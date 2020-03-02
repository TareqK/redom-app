/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.rest;

import java.util.List;
import me.kisoft.qahwagi.domain.core.entity.Customer;
import me.kisoft.qahwagi.infra.rest.service.RestService;

/**
 *
 * @author tareq
 */
public class CustomerRestService extends RestService<Customer> {

  @Override
  public List<Customer> findAll() {
    return null;
  }

  @Override
  public Customer findOne(String id) {
    return null;
  }

  @Override
  public void save(Customer toSave) {
  }

  @Override
  public void update(Customer toUpdate, String id) {
  }

  @Override
  public void delete(String id) {
  }

  @Override
  public Class<Customer> getType() {
    return Customer.class;
  }

}
