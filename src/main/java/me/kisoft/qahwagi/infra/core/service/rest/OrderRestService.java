/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.rest;

import io.javalin.http.Context;
import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.core.entity.Order;
import me.kisoft.qahwagi.domain.core.service.OrderService;
import me.kisoft.qahwagi.infra.core.factory.OrderServiceFactory;

/**
 *
 * @author tareq
 */
public class OrderRestService {

  OrderService service = OrderServiceFactory.getInstance().get();

  public void findOrders(Context ctx) {
    User user = ctx.sessionAttribute("user");
    ctx.json(service.getCustomerOrders(user.getId()));
  }

  public void updateOrderStatus(Context ctx) {
    User user = ctx.sessionAttribute("user");
    Order updated = service.updateBaristaOrderStatus(ctx.bodyAsClass(Order.class).getOrderStatus(), ctx.pathParam("id"), user.getId());
    if (updated != null) {
      ctx.json(updated);
    } else {
      ctx.res.setStatus(404);
    }
  }
}
