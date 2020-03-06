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

/**
 *
 * @author tareq
 */
@Data
public class MenuItem implements QahwagiEntity {

  private String id;
  private String name;
  private String description;
  private double price;
  private boolean available;

  @Override
  public void postCreated() {
    EventBus.getInstance().post(new DomainEvent("menuItemCreated", this));
  }

  @Override
  public void postUpdated() {
    EventBus.getInstance().post(new DomainEvent("menuItemUpdate", this));
  }

  @Override
  public void postDeleted() {
    EventBus.getInstance().post(new DomainEvent("menuItemDelete", this));
  }
}
