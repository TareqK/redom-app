/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.rest;

import java.util.List;
import me.kisoft.qahwagi.domain.core.entity.Customer;
import me.kisoft.qahwagi.domain.core.repo.CustomerRepository;
import me.kisoft.qahwagi.infra.core.factory.CustomerRepositoryFactory;
import me.kisoft.qahwagi.infra.rest.service.RestService;

/**
 *
 * @author tareq
 */
public class CustomerRestService extends RestService<Customer> {

  @Override
  public List<Customer> findAll() throws Exception {
    try (CustomerRepository repo = CustomerRepositoryFactory.getInstance().get()) {
      return repo.findAll();
    }
  }

  @Override
  public Customer findOne(String id) throws Exception {
    try (CustomerRepository repo = CustomerRepositoryFactory.getInstance().get()) {
      return repo.findById(id);
    }
  }

  @Override
  public void save(Customer toSave) throws Exception {
    try (CustomerRepository repo = CustomerRepositoryFactory.getInstance().get()) {
      repo.save(toSave);
    }
  }

  @Override
  public void update(Customer toUpdate, String id) throws Exception {
    try (CustomerRepository repo = CustomerRepositoryFactory.getInstance().get()) {
      repo.update(toUpdate, id);
    }
  }

  @Override
  public void delete(String id) throws Exception {
    try (CustomerRepository repo = CustomerRepositoryFactory.getInstance().get()) {
      repo.delete(id);
    }
  }

  @Override
  public Class<Customer> getType() {
    return Customer.class;
  }

}
