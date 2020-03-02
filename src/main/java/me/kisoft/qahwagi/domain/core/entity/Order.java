/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.entity;

import java.util.List;
import lombok.Data;
import me.kisoft.qahwagi.domain.core.valo.MenuItem;
import me.kisoft.qahwagi.event.DomainEvent;
import me.kisoft.qahwagi.event.EventBus;

/**
 *
 * @author tareq
 */
@Data
public class Order {

  private String id;
  private String customerId;
  private String coffeeShopId;
  private List<MenuItem> orderedItems;

  public void postCreated() {
    EventBus.getInstance().post(new DomainEvent("orderCreated", this));
  }

  public void postUpdated() {
    EventBus.getInstance().post(new DomainEvent("orderUpdated", this));
  }

  public void postDeleted() {
    EventBus.getInstance().post(new DomainEvent("orderDeleted", this));
  }
}
