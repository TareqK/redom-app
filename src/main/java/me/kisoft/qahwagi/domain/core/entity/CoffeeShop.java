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
import org.apache.commons.lang3.StringUtils;

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

  public Order createOrder(Customer customer, List<String> menuItemIds, double latitude, double longitude) {
    if (isTakingOrders() && isWithinServingRadius(latitude, longitude)) {
      Order o = new Order();
      o.setCoffeeShop(this);
      o.setCustomer(customer);
      o.setLatitude(latitude);
      o.setLongitude(longitude);
      for (String itemId : menuItemIds) {
        if (offerings.stream().anyMatch(menuItem -> StringUtils.equals(menuItem.getId(), itemId))) {
          OrderedItem toAdd = new OrderedItem(itemId);
          o.getOrderedItems().add(toAdd);
        }
      }
      return o;
    } else {
      return null;
    }
  }

  public boolean isWithinServingRadius(double latitude, double longitude) {
    return true;
  }

}
