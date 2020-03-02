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
import me.kisoft.qahwagi.domain.auth.entity.UserRole;
import static me.kisoft.qahwagi.domain.auth.entity.UserRole.NONE;
import static me.kisoft.qahwagi.domain.auth.entity.UserRole.ROLE_BARISTA;
import static me.kisoft.qahwagi.domain.auth.entity.UserRole.ROLE_CUSTOMER;
import me.kisoft.qahwagi.infra.auth.service.rest.UserRestService;
import me.kisoft.qahwagi.infra.core.service.rest.CoffeeShopRestService;
import me.kisoft.qahwagi.infra.core.service.rest.OrderRestService;

/**
 *
 * @author tareq
 */
public class Main {

  public static void main(String[] args) {
    Javalin app = Javalin.create().start(7000);

    // Set the access-manager that Javalin should use
    app.config.accessManager((handler, ctx, permittedRoles) -> {
      UserRole userRole = getUserRole(ctx);
      if (permittedRoles.contains(userRole)) {
        handler.handle(ctx);
      } else {
        ctx.status(401).result("Unauthorized");
      }
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
        get(ctx -> new CoffeeShopRestService().getAll(ctx), roles(ROLE_BARISTA, ROLE_CUSTOMER));
        put(ctx -> new CoffeeShopRestService().updateMyShop(ctx), roles(ROLE_BARISTA));
        path("find", () -> {
          get(ctx -> new CoffeeShopRestService().findNearest(ctx), roles(ROLE_CUSTOMER));
        });
      });
    });
  }

  public static UserRole getUserRole(Context ctx) {
    UserRole role = ctx.sessionAttribute("role");
    if (role == null) {
      return UserRole.NONE;
    }
    return role;
  }

}
