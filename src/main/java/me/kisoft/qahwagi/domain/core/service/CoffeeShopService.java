/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.service;

import java.util.List;
import me.kisoft.qahwagi.domain.core.entity.CoffeeShop;
import me.kisoft.qahwagi.domain.core.entity.Order;

/**
 *
 * @author tareq
 */
public interface CoffeeShopService {

  public List<CoffeeShop> getServingCoffeeShops();

  public List<CoffeeShop> getServingShopsNear(double latitude, double longitude);

  public CoffeeShop getCoffeeShop(String baristaId);

  public CoffeeShop getBaristaCoffeeShop(String baristaId);

  public void updateCoffeeShopOfBarista(CoffeeShop coffeeShop, String baristaId);

  public Order createOrder(String coffeeShopId, String customerId, List<String> menuItemIds, double latitude, double longitude);

  public List<Order> getCoffeeShopOrdersForBarista(String coffeeShopId);

}
