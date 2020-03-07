/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.kisoft.qahwagi.domain.entity.QahwagiEntity;
import me.kisoft.qahwagi.domain.event.DomainEvent;
import me.kisoft.qahwagi.domain.event.EventBus;

/**
 *
 * @author tareq
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class OrderedItem implements QahwagiEntity {

  private String id;
  @NonNull
  private String menuItemId;

  @Override
  public void postDeleted() {
    EventBus.getInstance().post(new DomainEvent("menuItemOrderCancelled", this));
  }

  @Override
  public void postSaved() {
    EventBus.getInstance().post(new DomainEvent("menuItemOrdered", this));
  }

  @Override
  public void postUpdated() {
    EventBus.getInstance().post(new DomainEvent("menuItemOrderUpdated", this));
  }

}
