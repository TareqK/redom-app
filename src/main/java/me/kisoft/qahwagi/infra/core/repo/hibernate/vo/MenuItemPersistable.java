/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.repo.hibernate.vo;

import javax.persistence.Entity;
import lombok.Data;
import me.kisoft.qahwagi.domain.core.entity.MenuItem;
import me.kisoft.qahwagi.infra.repo.hibernate.vo.HibernatePersistable;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author tareq
 */
@Data
@Entity
public class MenuItemPersistable extends HibernatePersistable<MenuItem> {

  private String name;
  private String description;
  private double price;
  private boolean available;

  @Override
  public MenuItem toDomainEntity() {
    MenuItem m = new MenuItem();
    m.setId(String.valueOf(getId()));
    m.setName(name);
    m.setDescription(description);
    m.setPrice(price);
    m.setAvailable(available);
    return m;
  }

  @Override
  public MenuItemPersistable fromDomainEntity(MenuItem domainEntity) {
    this.setId(NumberUtils.toLong(domainEntity.getId()));
    this.name = domainEntity.getName();
    this.description = domainEntity.getDescription();
    this.price = domainEntity.getPrice();
    this.available = domainEntity.isAvailable();
    return this;
  }
}
