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
public class Order implements QahwagiEntity {

  private String id;
  private Customer customer = new Customer();
  private CoffeeShop coffeeShop = new CoffeeShop();
  private double latitude;
  private double longitude;
  private double notes;
  private OrderStatus orderStatus = OrderStatus.STARTED;
  private List<OrderedItem> orderedItems = new ArrayList<>();

  @Override
  public void postDeleted() {
    EventBus.getInstance().post(new DomainEvent("orderDeleted", this));
  }

  @Override
  public void postSaved() {
    EventBus.getInstance().post(new DomainEvent("orderSaved", this));
  }

  @Override
  public void postUpdated() {
    EventBus.getInstance().post(new DomainEvent("orderUpdated", this));
  }

}
