/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.repo;

import java.util.Date;
import java.util.List;
import me.kisoft.qahwagi.domain.core.entity.Order;
import me.kisoft.qahwagi.repo.CrudRepository;

/**
 *
 * @author tareq
 */
public interface OrderRepository extends CrudRepository<Order> {

  List<Order> getCustomerOrders(String customerId);

  List<Order> getCoffeeShopOrders(String coffeeShopId);

  List<Order> getCustomerOrdersBetweenDates(String customerId, Date from, Date to);

  List<Order> getCoffeeShopOrdersBetweenDates(String customerId, Date from, Date to);
}
