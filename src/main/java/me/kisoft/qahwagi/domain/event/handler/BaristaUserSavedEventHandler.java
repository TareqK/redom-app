/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.event.handler;

import lombok.extern.java.Log;
import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.auth.entity.UserRole;
import me.kisoft.qahwagi.domain.core.entity.Barista;
import me.kisoft.qahwagi.domain.core.repo.BaristaRepository;
import me.kisoft.qahwagi.domain.event.DomainEventHandler;
import me.kisoft.qahwagi.domain.event.EventHandler;
import me.kisoft.qahwagi.infra.core.factory.BaristaRepositoryFactory;

/**
 *
 * @author tareq
 */
@Log
@EventHandler
public class BaristaUserSavedEventHandler extends DomainEventHandler<User> {

  public BaristaUserSavedEventHandler() {
  }

  @Override
  public String getEventName() {
    return "userSaved";
  }

  @Override
  public void doHandle(User eventData) throws Exception {
    if (eventData.getUserRole() == UserRole.ROLE_BARISTA) {
      try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
        log.info("User of Role Barista was Created, Creating Barista");
        Barista barisata = new Barista();
        barisata.setId(eventData.getId());
        repo.save(barisata);
        log.info("Barista Created");

      }
    }
  }

  @Override
  public Class<User> getType() {
    return User.class;
  }

}
