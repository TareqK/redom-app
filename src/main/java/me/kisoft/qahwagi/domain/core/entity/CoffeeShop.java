/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.entity;

import java.util.List;
import lombok.Data;
import me.kisoft.qahwagi.event.DomainEvent;
import me.kisoft.qahwagi.event.EventBus;

/**
 *
 * @author tareq
 */
@Data
public class CoffeeShop {

  private String id;
  private String name;
  private double longitude;
  private double latitude;
  private String telephoneNumber;
  private double servingRadius;
  private boolean takingOrders;
  private List<MenuItem> offerings;

  public void postCreated() {
    EventBus.getInstance().post(new DomainEvent("coffeeShopCreated", this));
  }

  public void postUpdated() {
    EventBus.getInstance().post(new DomainEvent("coffeeShopUpdated", this));
  }

  public void postDeleted() {
    EventBus.getInstance().post(new DomainEvent("coffeeShopDeleted", this));
  }
}
