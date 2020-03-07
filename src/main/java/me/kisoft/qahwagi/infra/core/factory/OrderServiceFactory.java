/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.factory;

import me.kisoft.qahwagi.domain.core.service.OrderService;
import me.kisoft.qahwagi.infra.core.service.impl.OrderServiceImpl;
import me.kisoft.qahwagi.infra.factory.Factory;

/**
 *
 * @author tareq
 */
public class OrderServiceFactory implements Factory<OrderService> {

  private static OrderServiceFactory instance = getInstance();

  private OrderServiceFactory() {

  }

  public static OrderServiceFactory getInstance() {
    if (instance == null) {
      instance = new OrderServiceFactory();
    }
    return instance;
  }

  @Override
  public OrderService get() {
    return new OrderServiceImpl();
  }

}
