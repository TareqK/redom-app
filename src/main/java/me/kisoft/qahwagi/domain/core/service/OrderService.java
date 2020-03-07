/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.service;

import java.util.List;
import me.kisoft.qahwagi.domain.core.entity.Order;
import me.kisoft.qahwagi.domain.core.entity.OrderStatus;

/**
 *
 * @author tareq
 */
public interface OrderService {

  List<Order> getCustomerOrders(String customerId);

  Order updateBaristaOrderStatus(OrderStatus status, String orderId, String baristaId);
}
