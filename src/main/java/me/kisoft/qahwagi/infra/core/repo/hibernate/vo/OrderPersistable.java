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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

  @ManyToOne
  private CustomerPersistable customer = new CustomerPersistable();

  @ManyToOne
  private CoffeeShopPersistable coffeeShop = new CoffeeShopPersistable();

  @OneToMany
  private List<MenuItemPersistable> orderedItems = new ArrayList<>();

  public OrderPersistable(Order domainEntity) {
    super(domainEntity);
  }

  public OrderPersistable() {
  }

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
  public OrderPersistable toPersistable(Order domainEntity) {
    this.setId(NumberUtils.toLong(domainEntity.getId()));
    this.customer = new CustomerPersistable(domainEntity.getCustomer());
    this.coffeeShop = new CoffeeShopPersistable(domainEntity.getCoffeeShop());
    domainEntity.getOrderedItems().parallelStream().forEach(cnsmr -> {
      orderedItems.add(new MenuItemPersistable(cnsmr));
    });
    return this;
  }

}
