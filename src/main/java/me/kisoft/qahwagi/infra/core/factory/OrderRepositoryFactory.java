/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.factory;

import me.kisoft.qahwagi.domain.core.repo.OrderRepository;
import me.kisoft.qahwagi.infra.core.repo.hibernate.OrderRepositoryHibernateImpl;
import me.kisoft.qahwagi.infra.factory.Factory;

/**
 *
 * @author tareq
 */
public class OrderRepositoryFactory implements Factory<OrderRepository> {

  private static OrderRepositoryFactory instance = getInstance();

  private OrderRepositoryFactory() {

  }

  public static final OrderRepositoryFactory getInstance() {
    if (instance == null) {
      instance = new OrderRepositoryFactory();
    }
    return instance;
  }

  @Override
  public OrderRepository get() {
    return new OrderRepositoryHibernateImpl();
  }

}
