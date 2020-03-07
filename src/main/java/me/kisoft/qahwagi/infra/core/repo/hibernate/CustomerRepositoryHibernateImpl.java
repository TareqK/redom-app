/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.repo.hibernate;

import javax.persistence.NoResultException;
import me.kisoft.qahwagi.domain.core.entity.Customer;
import me.kisoft.qahwagi.domain.core.repo.CustomerRepository;
import me.kisoft.qahwagi.infra.core.repo.hibernate.vo.CustomerPersistable;
import me.kisoft.qahwagi.infra.repo.hiberante.HibernateCrudRepository;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author tareq
 */
public class CustomerRepositoryHibernateImpl extends HibernateCrudRepository<Customer, CustomerPersistable> implements CustomerRepository {

  @Override
  public Customer findById(String id) {
    CustomerPersistable foundCustomer = getPersistableByUserId(id);
    if (foundCustomer != null) {
      return foundCustomer.toDomainEntity();
    }
    return null;

  }

  private CustomerPersistable getPersistableByUserId(String userId) {
    try {
      return getEm().createNamedQuery("CustomerPersistable.byUserId", CustomerPersistable.class)
       .setParameter("user_id", NumberUtils.toLong(userId))
       .getSingleResult();
    } catch (NoResultException ex) {
      return null;
    }
  }

  @Override
  public Customer update(Customer toUpdate, String id) {
    toUpdate.setId(id);
    CustomerPersistable foundCustomer = getPersistableByUserId(id);
    if (foundCustomer != null) {
      CustomerPersistable updatedCustomer = new CustomerPersistable(toUpdate);
      updatedCustomer.setId(foundCustomer.getId());
      getEm().getTransaction().begin();
      try {
        getEm().merge(updatedCustomer);
      } finally {
        getEm().getTransaction().commit();
      }
      toUpdate = updatedCustomer.toDomainEntity();
      toUpdate.postUpdated();
    }
    return toUpdate;
  }

  @Override
  public void delete(String id) {
    CustomerPersistable foundCustomer = getPersistableByUserId(id);
    if (foundCustomer != null) {
      getEm().getTransaction().begin();
      try {
        getEm().remove(foundCustomer);
      } finally {
        getEm().getTransaction().commit();
      }
      foundCustomer.toDomainEntity().postDeleted();
    }
  }

  @Override
  public Class<Customer> getType() {
    return Customer.class;
  }

  @Override
  public Class<CustomerPersistable> getPersistable() {
    return CustomerPersistable.class;
  }

}
