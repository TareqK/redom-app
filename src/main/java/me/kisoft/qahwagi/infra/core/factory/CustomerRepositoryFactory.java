/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.factory;

import me.kisoft.qahwagi.domain.core.repo.CustomerRepository;
import me.kisoft.qahwagi.infra.core.repo.hibernate.CustomerRepositoryHibernateImpl;
import me.kisoft.qahwagi.infra.factory.Factory;

/**
 *
 * @author tareq
 */
public class CustomerRepositoryFactory implements Factory<CustomerRepository> {

  private static CustomerRepositoryFactory instance = getInstance();

  private CustomerRepositoryFactory() {

  }

  public static final CustomerRepositoryFactory getInstance() {
    if (instance == null) {
      instance = new CustomerRepositoryFactory();
    }
    return instance;
  }

  @Override
  public CustomerRepository get() {
    return new CustomerRepositoryHibernateImpl();
  }

}
