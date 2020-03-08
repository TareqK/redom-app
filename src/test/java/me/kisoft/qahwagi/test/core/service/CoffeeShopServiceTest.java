/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.test.core.service;

import me.kisoft.qahwagi.domain.core.entity.CoffeeShop;
import me.kisoft.qahwagi.domain.core.entity.MenuItem;
import me.kisoft.qahwagi.infra.core.service.rest.vo.OrderRequest;
import me.kisoft.qahwagi.test.QahwagiTest;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
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

  @Test
  public void getAllShopsTest() {
    asCustomer().get("/shop").then().statusCode(200).and().body("size()", not(0));
  }

  @Test
  public void findNearest() {
    CoffeeShop coffeeShop = new CoffeeShop();
    coffeeShop.setTakingOrders(true);
    coffeeShop.setName("The Flying Dragon");
    coffeeShop.setLatitude(-3333333);
    coffeeShop.setLongitude(-3333333);
    coffeeShop.setServingRadius(300);
    asBarista().body(coffeeShop).put("/shop/myshop").then().statusCode(200);
    asCustomer().queryParam("latitude", -3333333)
     .queryParam("longitude", -3333333)
     .get("shop").then().statusCode(200)
     .and().body("size()", not(0));
  }

  @Test
  public void createOrderTest() {
    CoffeeShop coffeeShop = new CoffeeShop();
    coffeeShop.setTakingOrders(true);
    coffeeShop.setName("The Flying Dragon");
    MenuItem item = new MenuItem();
    item.setAvailable(true);
    item.setName("Arabic Coffee");
    item.setDescription("Very Nice Very Cheap");

    coffeeShop.getOfferings().add(item);
    asBarista().body(coffeeShop).put("/shop/myshop").then().statusCode(200);
    CoffeeShop[] shops = asCustomer().get("/shop").as(CoffeeShop[].class);
    String shopId = shops[0].getId();
    OrderRequest request = new OrderRequest();
    request.getOrderedItems().add(shops[0].getOfferings().get(0).getId());
    request.getOrderedItems().add(shops[0].getOfferings().get(0).getId());
    request.getOrderedItems().add(shops[0].getOfferings().get(0).getId());

    asCustomer().body(request).pathParam("id", shopId).post("/shop/{id}/order").then().statusCode(200)
     .and().body("orderedItems", hasSize(equalTo(3)));
  }

  @Test
  public void createInvalidOrderTest() {
    CoffeeShop coffeeShop = new CoffeeShop();
    coffeeShop.setTakingOrders(false);
    coffeeShop.setName("The Flying Dragon");
    MenuItem item = new MenuItem();
    item.setAvailable(true);
    item.setName("Arabic Coffee");
    item.setDescription("Very Nice Very Cheap");

    coffeeShop.getOfferings().add(item);
    asBarista().body(coffeeShop).put("/shop/myshop").then().statusCode(200);
    CoffeeShop[] shops = asCustomer().get("/shop").as(CoffeeShop[].class);
    String shopId = shops[0].getId();
    OrderRequest request = new OrderRequest();
    request.getOrderedItems().add(shops[0].getOfferings().get(0).getId());
    request.getOrderedItems().add(shops[0].getOfferings().get(0).getId());
    request.getOrderedItems().add(shops[0].getOfferings().get(0).getId());

    asCustomer().body(request).pathParam("id", shopId).post("/shop/{id}/order").then().statusCode(409);
  }

  @Test
  public void getShopOrders() {
    CoffeeShop coffeeShop = new CoffeeShop();
    coffeeShop.setTakingOrders(true);
    coffeeShop.setName("The Flying Dragon");
    MenuItem item = new MenuItem();
    item.setAvailable(true);
    item.setName("Arabic Coffee");
    item.setDescription("Very Nice Very Cheap");

    coffeeShop.getOfferings().add(item);
    asBarista().body(coffeeShop).put("/shop/myshop").then().statusCode(200);
    CoffeeShop[] shops = asCustomer().get("/shop").as(CoffeeShop[].class);
    String shopId = shops[0].getId();
    OrderRequest request = new OrderRequest();
    request.getOrderedItems().add(shops[0].getOfferings().get(0).getId());
    request.getOrderedItems().add(shops[0].getOfferings().get(0).getId());
    request.getOrderedItems().add(shops[0].getOfferings().get(0).getId());
    asCustomer().body(request).pathParam("id", shopId).post("/shop/{id}/order").then().statusCode(200)
     .and().body("orderedItems", hasSize(equalTo(3)));
    asBarista().get("/shop/myshop/orders").then().statusCode(200).and().body("size()", not(0));
  }
}
