/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.rest;

import io.javalin.http.Context;
import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.core.entity.CoffeeShop;
import me.kisoft.qahwagi.domain.core.entity.Order;
import me.kisoft.qahwagi.domain.core.service.CoffeeShopService;
import me.kisoft.qahwagi.infra.core.factory.CoffeeShopServiceFactory;
import me.kisoft.qahwagi.infra.core.service.rest.vo.OrderRequest;

/**
 *
 * @author tareq
 */
public class CoffeeShopRestService {

  CoffeeShopService service = CoffeeShopServiceFactory.getInstance().get();

  public void getShopOrders(Context ctx) {
    User u = ctx.sessionAttribute("user");
    ctx.json(service.getCoffeeShopOrdersForBarista(u.getId()));
  }

  public void getAll(Context ctx) {
    ctx.json(service.getServingCoffeeShops());
  }

  public void findNearest(Context ctx) {
    ctx.json(service.getServingShopsNear(ctx.queryParam("latitude", Long.class).get(), ctx.queryParam("longitude", Long.class).get()));
  }

  public void getMyShop(Context ctx) {
    User u = ctx.sessionAttribute("user");
    ctx.json(service.getBaristaCoffeeShop(u.getId()));
  }

  public void updateMyShop(Context ctx) {
    User u = ctx.sessionAttribute("user");
    service.updateCoffeeShopOfBarista(ctx.bodyAsClass(CoffeeShop.class), u.getId());
  }

  public void createOrder(Context ctx) {
    User u = ctx.sessionAttribute("user");
    OrderRequest request = ctx.bodyAsClass(OrderRequest.class);
    Order createdOrder = service.createOrder(ctx.pathParam("id"), u.getId(), request.getOrderedItems(), request.getLatitude(), request.getLongitude());
    if (createdOrder != null) {
      ctx.json(createdOrder);
    } else {
      ctx.res.setStatus(409);
    }
  }

}
