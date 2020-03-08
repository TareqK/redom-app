/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.test.core.service;

import me.kisoft.qahwagi.domain.core.entity.CoffeeShop;
import me.kisoft.qahwagi.domain.core.entity.MenuItem;
import me.kisoft.qahwagi.test.QahwagiTest;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import org.junit.Test;

/**
 *
 * @author tareq
 */
public class CoffeeShopServiceTest extends QahwagiTest {

  @Test
  public void readMyShopTest() {
    asBarista().get("/shop/myshop").then().statusCode(200).and().body("id", notNullValue());
  }

  @Test
  public void updateMyShopTest() {
    CoffeeShop coffeeShop = new CoffeeShop();
    coffeeShop.setTakingOrders(true);
    coffeeShop.setName("The Flying Dragon");
    MenuItem item = new MenuItem();
    item.setAvailable(true);
    item.setName("Arabic Coffee");
    item.setDescription("Very Nice Very Cheap");

    coffeeShop.getOfferings().add(item);
    asBarista().body(coffeeShop).put("/shop/myshop").then().statusCode(200);
    asBarista().get("/shop/myshop").then().statusCode(200).and()
     .body("name", equalTo("The Flying Dragon")).and()
     .body("offerings", hasSize(equalTo(1)));
  }
}
