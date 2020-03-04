/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.repo.hibernate.vo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import me.kisoft.qahwagi.domain.core.entity.MenuItem;
import me.kisoft.qahwagi.infra.vo.Transformable;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author tareq
 */
@Data
public class MenuItemPersistable implements Transformable<MenuItem> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String description;
  private double price;
  private boolean available;

  @Override
  public MenuItem toDomainEntity() {
    MenuItem m = new MenuItem();
    m.setId(String.valueOf(id));
    m.setName(name);
    m.setDescription(description);
    m.setPrice(price);
    m.setAvailable(available);
    return m;
  }

  @Override
  public MenuItemPersistable fromDomainEntity(MenuItem domainEntity) {
    this.id = NumberUtils.toLong(domainEntity.getId());
    this.name = domainEntity.getName();
    this.description = domainEntity.getDescription();
    this.price = domainEntity.getPrice();
    this.available = domainEntity.isAvailable();
    return this;
  }
}
