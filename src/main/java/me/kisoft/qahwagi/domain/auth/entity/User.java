/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.auth.entity;

import lombok.Data;
import me.kisoft.qahwagi.event.DomainEvent;
import me.kisoft.qahwagi.event.EventBus;

/**
 *
 * @author tareq
 */
@Data
public class User {

  private String id;
  private String username;
  private String password;
  private UserRole userRole;
  private String telephoneNumber;

  public void postCreated() {
    EventBus.getInstance().post(new DomainEvent("userCreated", this));
  }

  public void postUpdated() {
    EventBus.getInstance().post(new DomainEvent("userUpdated", this));
  }

  public void postDeleted() {
    EventBus.getInstance().post(new DomainEvent("userDeleted", this));
  }
}
