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
public class Barista implements QahwagiEntity {

  private String id;
  private CoffeeShop coffeeShop = new CoffeeShop();

  @Override
  public void postDeleted() {
    EventBus.getInstance().post(new DomainEvent("baristaDeleted", this));
  }

  @Override
  public void postSaved() {
    EventBus.getInstance().post(new DomainEvent("batistaSaved", this));
  }

  @Override
  public void postUpdated() {
    EventBus.getInstance().post(new DomainEvent("batistaUpdated", this));
  }

}
