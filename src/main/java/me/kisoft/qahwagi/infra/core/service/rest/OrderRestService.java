/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.rest;

import java.util.List;
import me.kisoft.qahwagi.domain.core.entity.Order;
import me.kisoft.qahwagi.domain.core.repo.OrderRepository;
import me.kisoft.qahwagi.infra.core.factory.OrderRepositoryFactory;
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
