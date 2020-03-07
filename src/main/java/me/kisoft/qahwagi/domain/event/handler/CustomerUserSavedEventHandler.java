/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.event.handler;

import lombok.extern.java.Log;
import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.auth.entity.UserRole;
import me.kisoft.qahwagi.domain.core.entity.Customer;
import me.kisoft.qahwagi.domain.core.repo.CustomerRepository;
import me.kisoft.qahwagi.domain.event.DomainEventHandler;
import me.kisoft.qahwagi.domain.event.EventHandler;
import me.kisoft.qahwagi.infra.core.factory.CustomerRepositoryFactory;

/**
 *
 * @author tareq
 */
@Log
@EventHandler
public class CustomerUserSavedEventHandler extends DomainEventHandler<User> {

  public CustomerUserSavedEventHandler() {
  }

  @Override
  public String getEventName() {
    return "userSaved";
  }

  @Override
  public void doHandle(User eventData) throws Exception {
    if (eventData.getUserRole() == UserRole.ROLE_CUSTOMER) {
      try (CustomerRepository repo = CustomerRepositoryFactory.getInstance().get()) {
        log.info("User of Role Customer was Created, Creating Customer");
        Customer customer = new Customer();
        customer.setId(eventData.getId());
        repo.save(customer);
        log.info("Customer Created");
      }
    }
  }

  @Override
  public Class<User> getType() {
    return User.class;
  }

}
