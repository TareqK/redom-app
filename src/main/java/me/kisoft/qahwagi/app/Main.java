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

    app.routes(() -> {
      path("user", () -> {
        path("signin", () -> {
          post(ctx -> new UserRestService().signIn(ctx), roles(NONE));
        });
        path("signup", () -> {
          post(ctx -> new UserRestService().signUp(ctx), roles(NONE));
        });
      });
      path("order", () -> {
        get(ctx -> new OrderRestService().getAll(ctx), roles(ROLE_CUSTOMER, ROLE_BARISTA));
        post(ctx -> new OrderRestService().create(ctx), roles(ROLE_CUSTOMER));
        path(":id", () -> {
          put(ctx -> new OrderRestService().update(ctx), roles(ROLE_BARISTA));
        });
      });
      path("shop", () -> {
        get(ctx -> new CoffeeShopRestService().getAll(ctx), roles(ROLE_CUSTOMER));
        path("find", () -> {
          get(ctx -> new CoffeeShopRestService().findNearest(ctx), roles(ROLE_CUSTOMER));
        });
        path("myshop", () -> {
          get(ctx -> new CoffeeShopRestService().getMyShop(ctx), roles(ROLE_BARISTA));
          put(ctx -> new CoffeeShopRestService().updateMyShop(ctx), roles(ROLE_BARISTA));
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
