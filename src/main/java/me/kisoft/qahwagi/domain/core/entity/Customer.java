/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.entity;

import lombok.Data;
import me.kisoft.qahwagi.event.DomainEvent;
import me.kisoft.qahwagi.event.EventBus;

/**
 *
 * @author tareq
 */
@Data
public class Customer {

  private String id;
  private String name;
  private String telephoneNumber;

  public void postCreated() {
    EventBus.getInstance().post(new DomainEvent("customerCreated", this));
  }

  public void postUpdated() {
    EventBus.getInstance().post(new DomainEvent("customerUpdated", this));
  }

  public void postDeleted() {
    EventBus.getInstance().post(new DomainEvent("customerDeleted", this));
  }
}
