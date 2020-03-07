/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.auth.entity;

import lombok.Data;
import me.kisoft.qahwagi.domain.entity.QahwagiEntity;
import me.kisoft.qahwagi.domain.event.DomainEvent;
import me.kisoft.qahwagi.domain.event.EventBus;

/**
 *
 * @author tareq
 */
@Data
public class User implements QahwagiEntity {

  private String id;
  private String username;
  private String password;
  private UserRole userRole;
  private String name;
  private String telephoneNumber;

  @Override
  public void postDeleted() {
    EventBus.getInstance().post(new DomainEvent("userDeleted", this));
  }

  @Override
  public void postSaved() {
    EventBus.getInstance().post(new DomainEvent("userSaved", this));
  }

  @Override
  public void postUpdated() {
    EventBus.getInstance().post(new DomainEvent("userUpdated", this));
  }
}
