/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.rest;

import java.util.List;
import me.kisoft.qahwagi.domain.core.entity.Order;
import me.kisoft.qahwagi.infra.rest.service.RestService;

/**
 *
 * @author tareq
 */
public class OrderRestService extends RestService<Order> {

  @Override
  public List<Order> findAll() {
    return null;
  }

  @Override
  public Order findOne(String id) {
    return null;
  }

  @Override
  public void save(Order toSave) {
  }

  @Override
  public void update(Order toUpdate, String id) {
  }

  @Override
  public void delete(String id) {
  }

  @Override
  public Class<Order> getType() {
    return Order.class;
  }

}
