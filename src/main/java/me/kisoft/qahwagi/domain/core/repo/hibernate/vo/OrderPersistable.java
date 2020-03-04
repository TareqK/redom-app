/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.repo.hibernate.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import me.kisoft.qahwagi.domain.core.entity.Order;
import me.kisoft.qahwagi.infra.vo.Transformable;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author tareq
 */
@Data
public class OrderPersistable implements Transformable<Order> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private CustomerPersistable customer = new CustomerPersistable();
  private CoffeeShopPersistable coffeeShop = new CoffeeShopPersistable();
  private List<MenuItemPersistable> orderedItems = new ArrayList<>();

  @Override
  public Order toDomainEntity() {
    Order o = new Order();
    o.setId(String.valueOf(id));
    o.setCustomer(customer.toDomainEntity());
    o.setCoffeeShop(coffeeShop.toDomainEntity());
    o.setOrderedItems(orderedItems.parallelStream().map(MenuItemPersistable::toDomainEntity).collect(Collectors.toList()));
    return o;

  }

  @Override
  public OrderPersistable fromDomainEntity(Order domainEntity) {
    this.id = NumberUtils.toLong(domainEntity.getId());
    this.customer = new CustomerPersistable().fromDomainEntity(domainEntity.getCustomer());
    this.coffeeShop = new CoffeeShopPersistable().fromDomainEntity(domainEntity.getCoffeeShop());
    domainEntity.getOrderedItems().parallelStream().forEach(cnsmr -> {
      orderedItems.add(new MenuItemPersistable().fromDomainEntity(cnsmr));
    });
    return this;
  }

}
