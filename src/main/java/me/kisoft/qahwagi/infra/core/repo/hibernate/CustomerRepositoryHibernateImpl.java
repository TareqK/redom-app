/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.repo.hibernate;

import me.kisoft.qahwagi.domain.core.entity.Customer;
import me.kisoft.qahwagi.domain.core.repo.CustomerRepository;
import me.kisoft.qahwagi.infra.core.repo.hibernate.vo.CustomerPersistable;
import me.kisoft.qahwagi.infra.repo.hiberante.HibernateCrudRepository;

/**
 *
 * @author tareq
 */
public class CustomerRepositoryHibernateImpl extends HibernateCrudRepository<Customer, CustomerPersistable> implements CustomerRepository {

  @Override
  public Class<Customer> getType() {
    return Customer.class;
  }

  @Override
  public Class<CustomerPersistable> getPersistable() {
    return CustomerPersistable.class;
  }

}
