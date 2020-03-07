/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.impl;

import java.util.List;
import lombok.SneakyThrows;
import me.kisoft.qahwagi.domain.core.entity.Barista;
import me.kisoft.qahwagi.domain.core.entity.Order;
import me.kisoft.qahwagi.domain.core.entity.OrderStatus;
import me.kisoft.qahwagi.domain.core.repo.BaristaRepository;
import me.kisoft.qahwagi.domain.core.repo.OrderRepository;
import me.kisoft.qahwagi.domain.core.service.OrderService;
import me.kisoft.qahwagi.infra.core.factory.BaristaRepositoryFactory;
import me.kisoft.qahwagi.infra.core.factory.OrderRepositoryFactory;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tareq
 */
public class OrderServiceImpl implements OrderService {

  @SneakyThrows
  @Override
  public List<Order> getCustomerOrders(String customerId) {
    try (OrderRepository repo = OrderRepositoryFactory.getInstance().get()) {
      return repo.getCustomerOrders(customerId);
    }
  }

  @SneakyThrows
  @Override
  public Order updateBaristaOrderStatus(OrderStatus status, String orderId, String baristaId) {
    Barista barista;
    try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
      barista = repo.findById(baristaId);
    }
    try (OrderRepository repo = OrderRepositoryFactory.getInstance().get()) {
      Order foundOrder = repo.findById(orderId);
      if (StringUtils.equals(foundOrder.getCoffeeShop().getId(), barista.getCoffeeShop().getId())) {
        foundOrder.setOrderStatus(status);
        return repo.update(foundOrder);
      }
    }
    return null;
  }

}
