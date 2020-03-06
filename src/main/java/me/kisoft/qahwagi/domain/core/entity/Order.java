/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.entity;

import java.util.List;
import lombok.Data;
import me.kisoft.qahwagi.domain.entity.QahwagiEntity;
import me.kisoft.qahwagi.domain.event.DomainEvent;
import me.kisoft.qahwagi.domain.event.EventBus;

/**
 *
 * @author tareq
 */
@Data
public class Order implements QahwagiEntity {

  private String id;
  private Customer customer;
  private CoffeeShop coffeeShop;
  private List<MenuItem> orderedItems;

  @Override
  public void postCreated() {
    EventBus.getInstance().post(new DomainEvent("orderCreated", this));
  }

  @Override
  public void postUpdated() {
    EventBus.getInstance().post(new DomainEvent("orderUpdated", this));
  }

  @Override
  public void postDeleted() {
    EventBus.getInstance().post(new DomainEvent("orderDeleted", this));
  }
}
