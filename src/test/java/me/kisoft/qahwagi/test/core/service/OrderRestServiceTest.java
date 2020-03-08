/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.test.core.service;

import me.kisoft.qahwagi.domain.core.entity.CoffeeShop;
import me.kisoft.qahwagi.domain.core.entity.MenuItem;
import me.kisoft.qahwagi.domain.core.entity.Order;
import me.kisoft.qahwagi.domain.core.entity.OrderStatus;
import me.kisoft.qahwagi.infra.core.service.rest.vo.OrderRequest;
import me.kisoft.qahwagi.test.QahwagiTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

/**
 *
 * @author tareq
 */
public class OrderRestServiceTest extends QahwagiTest {

  @Test
  public void getCustomerOrdersTest() {
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

    asCustomer().body(request).pathParam("id", shopId).post("/shop/{id}/order").then().statusCode(200);
    asCustomer().body(request).pathParam("id", shopId).post("/shop/{id}/order").then().statusCode(200);
    asCustomer().get("order").then().statusCode(200).and().body("size()", is(not(0)));
  }

  @Test
  public void updateOrdersTest() {
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

    asCustomer().body(request).pathParam("id", shopId).post("/shop/{id}/order").then().statusCode(200);

    Order[] orders = asBarista().get("/shop/myshop/orders").as(Order[].class);

    Order updatedOrder = new Order();
    updatedOrder.setOrderStatus(OrderStatus.DELIVERING);
    asBarista().body(updatedOrder).pathParam("id", orders[0].getId())
     .put("/order/{id}/status").then().statusCode(200);

    assertEquals(OrderStatus.DELIVERING, asCustomer().get("order").as(Order[].class)[0].getOrderStatus());
    assertEquals(orders[0].getId(), asCustomer().get("order").as(Order[].class)[0].getId());
    assertNotEquals(orders[0].getOrderStatus(), asCustomer().get("order").as(Order[].class)[0].getOrderStatus());
  }
}
