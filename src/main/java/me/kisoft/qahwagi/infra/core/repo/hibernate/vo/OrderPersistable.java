/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.repo.hibernate.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import lombok.Data;
import me.kisoft.qahwagi.domain.core.entity.Order;
import me.kisoft.qahwagi.infra.repo.hibernate.vo.HibernatePersistable;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author tareq
 */
@Data
@Entity
public class OrderPersistable extends HibernatePersistable<Order> {

  private CustomerPersistable customer = new CustomerPersistable();
  private CoffeeShopPersistable coffeeShop = new CoffeeShopPersistable();
  private List<MenuItemPersistable> orderedItems = new ArrayList<>();

  @Override
  public Order toDomainEntity() {
    Order o = new Order();
    o.setId(String.valueOf(getId()));
    o.setCustomer(customer.toDomainEntity());
    o.setCoffeeShop(coffeeShop.toDomainEntity());
    o.setOrderedItems(orderedItems.parallelStream().map(MenuItemPersistable::toDomainEntity).collect(Collectors.toList()));
    return o;

  }

  @Override
  public OrderPersistable fromDomainEntity(Order domainEntity) {
    this.setId(NumberUtils.toLong(domainEntity.getId()));
    this.customer = new CustomerPersistable().fromDomainEntity(domainEntity.getCustomer());
    this.coffeeShop = new CoffeeShopPersistable().fromDomainEntity(domainEntity.getCoffeeShop());
    domainEntity.getOrderedItems().parallelStream().forEach(cnsmr -> {
      orderedItems.add(new MenuItemPersistable().fromDomainEntity(cnsmr));
    });
    return this;
  }

}
