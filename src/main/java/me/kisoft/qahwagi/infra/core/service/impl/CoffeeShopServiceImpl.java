/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.impl;

import java.util.List;
import lombok.SneakyThrows;
import me.kisoft.qahwagi.domain.core.entity.Barista;
import me.kisoft.qahwagi.domain.core.entity.CoffeeShop;
import me.kisoft.qahwagi.domain.core.entity.Customer;
import me.kisoft.qahwagi.domain.core.entity.Order;
import me.kisoft.qahwagi.domain.core.repo.BaristaRepository;
import me.kisoft.qahwagi.domain.core.repo.CoffeeShopRepository;
import me.kisoft.qahwagi.domain.core.repo.OrderRepository;
import me.kisoft.qahwagi.domain.core.service.CoffeeShopService;
import me.kisoft.qahwagi.infra.core.factory.BaristaRepositoryFactory;
import me.kisoft.qahwagi.infra.core.factory.CoffeeShopRepositoryFactory;
import me.kisoft.qahwagi.infra.core.factory.OrderRepositoryFactory;

/**
 *
 * @author tareq
 */
public class CoffeeShopServiceImpl implements CoffeeShopService {

  @SneakyThrows
  @Override
  public List<CoffeeShop> getServingCoffeeShops() {
    try (CoffeeShopRepository repo = CoffeeShopRepositoryFactory.getInstance().get()) {
      return repo.findAll();
    }
  }

  @SneakyThrows
  @Override
  public List<CoffeeShop> getServingShopsNear(double latitude, double longitude) {
    try (CoffeeShopRepository repo = CoffeeShopRepositoryFactory.getInstance().get()) {
      return repo.findAll();
    }
  }

  @SneakyThrows
  @Override
  public CoffeeShop getCoffeeShop(String baristaId) {
    try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
      return repo.findById(baristaId).getCoffeeShop();
    }
  }

  @SneakyThrows
  @Override
  public void updateCoffeeShopOfBarista(CoffeeShop coffeeShop, String baristaId) {
    try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
      Barista barista = repo.findById(baristaId);
      coffeeShop.setId(barista.getCoffeeShop().getId());
      barista.setCoffeeShop(coffeeShop);
      repo.update(barista);
    }
  }

  @SneakyThrows
  @Override
  public CoffeeShop getBaristaCoffeeShop(String baristaId) {
    try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
      return repo.findById(baristaId).getCoffeeShop();
    }
  }

  @SneakyThrows
  @Override
  public Order createOrder(String coffeeShopId, String customerId, List<String> menuItemIds, double latitude, double longitude) {
    Order order;
    try (CoffeeShopRepository repo = CoffeeShopRepositoryFactory.getInstance().get()) {
      CoffeeShop shop = repo.findById(coffeeShopId);
      Customer customer = new Customer();
      customer.setId(customerId);
      order = shop.createOrder(customer, menuItemIds, latitude, longitude);

    }
    if (order != null) {
      try (OrderRepository repo = OrderRepositoryFactory.getInstance().get()) {
        repo.save(order);
      }
    }
    return order;
  }

  @SneakyThrows
  @Override
  public List<Order> getCoffeeShopOrdersForBarista(String baristaId) {
    Barista barista;
    try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
      barista = repo.findById(baristaId);
    }
    try (OrderRepository repo = OrderRepositoryFactory.getInstance().get()) {
      return repo.getCoffeeShopOrders(barista.getCoffeeShop().getId());
    }
  }

}
