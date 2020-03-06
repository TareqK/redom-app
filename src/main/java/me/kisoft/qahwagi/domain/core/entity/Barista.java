/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.entity;

import lombok.Data;
import me.kisoft.qahwagi.domain.entity.QahwagiEntity;
import me.kisoft.qahwagi.event.DomainEvent;
import me.kisoft.qahwagi.event.EventBus;

/**
 *
 * @author tareq
 */
@Data
public class Barista implements QahwagiEntity {

  private String id;
  private CoffeeShop coffeeShop;

  @Override
  public void postCreated() {
    EventBus.getInstance().post(new DomainEvent("baristaCreated", this));
  }

  @Override
  public void postUpdated() {
    EventBus.getInstance().post(new DomainEvent("batistaUpdated", this));
  }

  @Override
  public void postDeleted() {
    EventBus.getInstance().post(new DomainEvent("baristaDeleted", this));
  }
}
