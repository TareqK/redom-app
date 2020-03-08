/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.repo.hibernate.vo;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import lombok.Data;
import me.kisoft.qahwagi.domain.core.entity.OrderedItem;
import me.kisoft.qahwagi.infra.repo.hibernate.vo.HibernatePersistable;

/**
 *
 * @author tareq
 */
@Data
@Entity
public class OrderedItemPersistable extends HibernatePersistable<OrderedItem> {

  @OneToOne
  private MenuItemPersistable menuItem;

  public OrderedItemPersistable(OrderedItem domainEntity) {
    super(domainEntity);
  }

  public OrderedItemPersistable() {
  }

  @Override
  protected HibernatePersistable toPersistable(OrderedItem domainEntity) {
    this.setId(domainEntity.getId());
    this.menuItem = new MenuItemPersistable();
    this.menuItem.setId(domainEntity.getMenuItemId());
    return this;
  }

  @Override
  public OrderedItem toDomainEntity() {
    OrderedItem orderedItem = new OrderedItem();
    orderedItem.setId(String.valueOf(this.getId()));
    orderedItem.setMenuItemId(String.valueOf(menuItem.getId()));
    return orderedItem;
  }

}
