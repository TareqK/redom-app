/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.rest;

import io.javalin.http.Context;
import java.util.List;
import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.core.entity.CoffeeShop;
import me.kisoft.qahwagi.domain.core.entity.Customer;
import me.kisoft.qahwagi.domain.core.entity.Order;
import me.kisoft.qahwagi.domain.core.repo.CoffeeShopRepository;
import me.kisoft.qahwagi.domain.core.repo.OrderRepository;
import me.kisoft.qahwagi.infra.core.factory.CoffeeShopRepositoryFactory;
import me.kisoft.qahwagi.infra.core.factory.OrderRepositoryFactory;
import me.kisoft.qahwagi.infra.core.service.rest.vo.OrderRequest;
import me.kisoft.qahwagi.infra.rest.service.RestService;

/**
 *
 * @author tareq
 */
public class OrderRestService extends RestService<Order> {

  @Override
  public List<Order> findAll() throws Exception {
    try (OrderRepository repo = OrderRepositoryFactory.getInstance().get()) {
      return repo.findAll();
    }
  }

  @Override
  public Order findOne(String id) throws Exception {
    try (OrderRepository repo = OrderRepositoryFactory.getInstance().get()) {
      return repo.findById(id);
    }
  }

  @Override
  public void save(Order toSave) throws Exception {
    try (OrderRepository repo = OrderRepositoryFactory.getInstance().get()) {
      repo.save(toSave);
    }
  }

  public void orderFromShop(Context ctx) throws Exception {
    OrderRequest orderRequest = ctx.bodyAsClass(OrderRequest.class);
    String shopId = ctx.pathParam("id");
    CoffeeShop shop = new CoffeeShop();
    shop.setId(shopId);
    Customer customer = new Customer();
    customer.setId(((User) ctx.sessionAttribute("user")).getId());
    orderRequest.setCoffeeShop(shop);
    orderRequest.setCustomer(customer);
    Order createdOrder = doOrder(orderRequest);
    if (createdOrder == null) {
      ctx.res.setStatus(409);
      return;
    }
    ctx.json(createdOrder);

  }

  public Order doOrder(OrderRequest request) throws Exception {
    Order createdOrder = null;
    try (CoffeeShopRepository coffeeShopRepository = CoffeeShopRepositoryFactory.getInstance().get()) {
      createdOrder = coffeeShopRepository.refresh(request.getCoffeeShop())
       .createOrder(request.getCustomer(), request.getOrderedItems(), request.getLatitude(), request.getLongitude());
    }
    if (createdOrder != null) {
      try (OrderRepository orderRepository = OrderRepositoryFactory.getInstance().get()) {
        createdOrder = orderRepository.save(createdOrder);
      }
    }
    return createdOrder;
  }

  @Override
  public void update(Order toUpdate, String id) throws Exception {
    try (OrderRepository repo = OrderRepositoryFactory.getInstance().get()) {
      repo.update(toUpdate, id);
    }
  }

  @Override
  public void delete(String id) throws Exception {
    try (OrderRepository repo = OrderRepositoryFactory.getInstance().get()) {
      repo.delete(id);
    }
  }

  @Override
  public Class<Order> getType() {
    return Order.class;
  }

}
