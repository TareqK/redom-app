/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.app;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;
import static io.javalin.core.security.SecurityUtil.roles;
import io.javalin.http.Context;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.java.Log;
import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.auth.entity.UserRole;
import static me.kisoft.qahwagi.domain.auth.entity.UserRole.NONE;
import static me.kisoft.qahwagi.domain.auth.entity.UserRole.ROLE_BARISTA;
import static me.kisoft.qahwagi.domain.auth.entity.UserRole.ROLE_CUSTOMER;
import me.kisoft.qahwagi.domain.event.EventBus;
import me.kisoft.qahwagi.infra.auth.service.rest.UserRestService;
import me.kisoft.qahwagi.infra.core.service.rest.CoffeeShopRestService;
import me.kisoft.qahwagi.infra.core.service.rest.OrderRestService;
import me.kisoft.qahwagi.infra.factory.EntityManagerFactory;

/**
 *
 * @author tareq
 */
@Log
public class Qahwagi {

  private static Javalin app;

  public static void main(String[] args) throws Throwable {
    EntityManagerFactory.getInstance().setPersistenceUnit("qahwagi_prod_PU");
    registerDomainHandlers();
    registerDerbyShutdownHook();
    startServer();
  }

  public static UserRole getUserRole(Context ctx) {
    User user = ctx.sessionAttribute("user");
    if (user == null || user.getUserRole() == null) {
      return UserRole.NONE;
    }
    return user.getUserRole();
  }

  private static void registerDomainHandlers() throws Throwable {
    EventBus.getInstance().searchForHandlers();
  }

  private static void registerDerbyShutdownHook() {
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          String jdbc = String.valueOf(EntityManagerFactory.getInstance().get().getEntityManagerFactory().getProperties().get("javax.persistence.jdbc.url"));
          DriverManager.getConnection(jdbc + ";shutdown=true");
        } catch (SQLException ex) {
          Logger.getLogger(Qahwagi.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }));
  }

  public static void startServer() {
    app = Javalin.create().start(7000);

    // Set the access-manager that Javalin should use
    app.config.accessManager((handler, ctx, permittedRoles) -> {
      if (!permittedRoles.contains(NONE)) {
        UserRole userRole = getUserRole(ctx);
        if (!permittedRoles.contains(userRole)) {
          ctx.status(401).result("Unauthorized");
          return;
        }
      }
      handler.handle(ctx);
    });

    UserRestService userService = new UserRestService();
    CoffeeShopRestService coffeeShopService = new CoffeeShopRestService();
    OrderRestService orderService = new OrderRestService();
    app.routes(() -> {
      path("user", () -> {
        path("signin", () -> {
          post(userService::signIn, roles(NONE));
        });
        path("signup", () -> {
          post(userService::signUp, roles(NONE));
        });
      });
      path("order", () -> {
        get(orderService::findOrders, roles(ROLE_CUSTOMER));
        path(":id/status", () -> {
          put(orderService::updateOrderStatus, roles(ROLE_BARISTA));
        });
      });
      path("shop", () -> {
        get(coffeeShopService::getAll, roles(ROLE_CUSTOMER));
        path("find", () -> {
          get(coffeeShopService::findNearest, roles(ROLE_CUSTOMER));
        });
        path("myshop", () -> {
          get(coffeeShopService::getMyShop, roles(ROLE_BARISTA));
          put(coffeeShopService::updateMyShop, roles(ROLE_BARISTA));
          path("orders", () -> {
            get(coffeeShopService::getShopOrders, roles(ROLE_BARISTA));
          });
        });

        path(":id", () -> {
          path("order", () -> {
            post(coffeeShopService::createOrder, roles(ROLE_CUSTOMER));
          });
        });
      });
    });
    app.config.addStaticFiles("/webapp");

  }

  public static void stopServer() {
    app.stop();
  }
}
