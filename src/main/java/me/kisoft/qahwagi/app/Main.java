/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.app;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.core.security.SecurityUtil.roles;
import io.javalin.http.Context;
import lombok.extern.java.Log;
import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.auth.entity.UserRole;
import static me.kisoft.qahwagi.domain.auth.entity.UserRole.NONE;
import static me.kisoft.qahwagi.domain.auth.entity.UserRole.ROLE_BARISTA;
import static me.kisoft.qahwagi.domain.auth.entity.UserRole.ROLE_CUSTOMER;
import me.kisoft.qahwagi.domain.event.DomainEventHandler;
import me.kisoft.qahwagi.domain.event.EventBus;
import me.kisoft.qahwagi.domain.event.EventHandler;
import me.kisoft.qahwagi.infra.auth.service.rest.UserRestService;
import me.kisoft.qahwagi.infra.core.service.rest.CoffeeShopRestService;
import me.kisoft.qahwagi.infra.core.service.rest.OrderRestService;
import me.kisoft.qahwagi.infra.factory.EntityManagerFactory;
import org.reflections.Reflections;

/**
 *
 * @author tareq
 */
@Log
public class Main {

  public static void main(String[] args) throws Throwable {
    EntityManagerFactory.getInstance().setPersistenceUnit("qahwagi_prod_PU");
    registerDomainHandlers();
    Javalin app = Javalin.create().start(7000);

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
    OrderRestService orderService = new OrderRestService();
    CoffeeShopRestService coffeeShopService = new CoffeeShopRestService();
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
        get(orderService::getAll, roles(ROLE_CUSTOMER, ROLE_BARISTA));
        post(orderService::create, roles(ROLE_CUSTOMER));
        path(":id", () -> {
          put(orderService::update, roles(ROLE_BARISTA));
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
        });
        path(":id", () -> {
          path("order", () -> {
            post(orderService::orderFromShop, roles(ROLE_CUSTOMER));
          });
        });
      });
    });
  }

  public static UserRole getUserRole(Context ctx) {
    User user = ctx.sessionAttribute("user");
    if (user == null || user.getUserRole() == null) {
      return UserRole.NONE;
    }
    return user.getUserRole();
  }

  private static void registerDomainHandlers() throws Throwable {
    Reflections r = new Reflections(DomainEventHandler.class);
    for (Class clazz : r.getTypesAnnotatedWith(EventHandler.class)) {
      Object o = clazz.getConstructor().newInstance();
      if (o instanceof DomainEventHandler) {
        EventBus.getInstance().subscribe((DomainEventHandler) o);
        log.info("Added Event Handler " + clazz.getSimpleName());
      }
    }
  }

}
