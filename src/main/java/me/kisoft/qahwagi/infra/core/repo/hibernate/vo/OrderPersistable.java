/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.repo.hibernate.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import me.kisoft.qahwagi.domain.core.entity.Customer;
import me.kisoft.qahwagi.domain.core.entity.Order;
import me.kisoft.qahwagi.domain.core.entity.OrderStatus;
import me.kisoft.qahwagi.infra.auth.repo.hibernate.vo.UserPersistable;
import me.kisoft.qahwagi.infra.repo.hibernate.vo.HibernatePersistable;

/**
 *
 * @author tareq
 */
@Data
@Entity
public class OrderPersistable extends HibernatePersistable<Order> {

  @OneToOne
  private UserPersistable user;

  @OneToOne
  private CoffeeShopPersistable coffeeShop;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @ManyToMany(cascade = CascadeType.ALL)
  private List<OrderedItemPersistable> orderedItems;

  public OrderPersistable(Order domainEntity) {
    super(domainEntity);
  }

  public OrderPersistable() {
  }

  @Override
  public Order toDomainEntity() {
    Order o = new Order();
    o.setId(getId());
    Customer c = new Customer();
    c.setId(user.toDomainEntity().getId());
    o.setCustomer(c);
    o.setCoffeeShop(coffeeShop.toDomainEntity());
    o.setOrderStatus(orderStatus);
    o.setOrderedItems(orderedItems.parallelStream().map(OrderedItemPersistable::toDomainEntity).collect(Collectors.toList()));
    return o;

  }

  @Override
  public OrderPersistable toPersistable(Order domainEntity) {
    this.setId(domainEntity.getId());
    this.user = new UserPersistable();
    this.user.setId(domainEntity.getCustomer().getId());
    this.coffeeShop = new CoffeeShopPersistable(domainEntity.getCoffeeShop());
    this.orderStatus = domainEntity.getOrderStatus();
    orderedItems = new ArrayList<>();
    domainEntity.getOrderedItems().parallelStream().forEach(cnsmr -> {
      orderedItems.add(new OrderedItemPersistable(cnsmr));
    });
    return this;
  }

}
