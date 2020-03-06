/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.repo.hibernate;

import java.util.Date;
import java.util.List;
import me.kisoft.qahwagi.domain.core.entity.Order;
import me.kisoft.qahwagi.domain.core.repo.OrderRepository;
import me.kisoft.qahwagi.infra.core.repo.hibernate.vo.OrderPersistable;
import me.kisoft.qahwagi.infra.repo.hiberante.HibernateCrudRepository;

/**
 *
 * @author tareq
 */
public class OrderRepositoryHibernateImpl extends HibernateCrudRepository<Order, OrderPersistable> implements OrderRepository {

  @Override
  public Class<Order> getType() {
    return Order.class;
  }

  @Override
  public Class<OrderPersistable> getPersistable() {
    return OrderPersistable.class;
  }

  @Override
  public List<Order> getCustomerOrders(String customerId) {
    return this.findAll();
  }

  @Override
  public List<Order> getCoffeeShopOrders(String coffeeShopId) {
    return this.findAll();
  }

  @Override
  public List<Order> getCustomerOrdersBetweenDates(String customerId, Date from, Date to) {
    return this.findAll();
  }

  @Override
  public List<Order> getCoffeeShopOrdersBetweenDates(String customerId, Date from, Date to) {
    return this.findAll();
  }

}
