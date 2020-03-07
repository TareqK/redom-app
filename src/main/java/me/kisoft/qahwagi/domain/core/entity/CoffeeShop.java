/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.entity;

import java.util.ArrayList;
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
public class CoffeeShop implements QahwagiEntity {

  private String id;
  private String name;
  private double longitude;
  private double latitude;
  private String telephoneNumber;
  private double servingRadius;
  private boolean takingOrders;
  private List<MenuItem> offerings = new ArrayList<>();

  @Override
  public void postDeleted() {
    EventBus.getInstance().post(new DomainEvent("coffeeShopDeleted", this));
  }

  @Override
  public void postSaved() {
    EventBus.getInstance().post(new DomainEvent("coffeeShopSaved", this));
  }

  @Override
  public void postUpdated() {
    EventBus.getInstance().post(new DomainEvent("coffeeShopUpdated", this));
  }

}
