/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.entity;

import lombok.Data;
import me.kisoft.qahwagi.domain.entity.QahwagiEntity;
import me.kisoft.qahwagi.domain.event.DomainEvent;
import me.kisoft.qahwagi.domain.event.EventBus;
import me.kisoft.qahwagi.domain.event.handler.CustomerUserSavedEventHandler;

/**
 *
 * @author tareq
 */
@Data
public class Customer implements QahwagiEntity {

  static {
    EventBus.getInstance().subscribe(new CustomerUserSavedEventHandler());
  }
  private String id;

  @Override
  public void postDeleted() {
    EventBus.getInstance().post(new DomainEvent("customerDeleted", this));
  }

  @Override
  public void postSaved() {
    EventBus.getInstance().post(new DomainEvent("customerSaved", this));
  }

  @Override
  public void postUpdated() {
    EventBus.getInstance().post(new DomainEvent("customerUpdated", this));
  }

}
