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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import me.kisoft.qahwagi.domain.core.entity.CoffeeShop;
import me.kisoft.qahwagi.infra.repo.hibernate.vo.HibernatePersistable;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author tareq
 */
@Data
@Entity
public class CoffeeShopPersistable extends HibernatePersistable<CoffeeShop> {

  private String name;
  private double longitude;
  private double latitude;
  private String telephoneNumber;
  private double servingRadius;
  private boolean takingOrders;

  @OneToOne(cascade = CascadeType.ALL)
  private BaristaPersistable barista;

  @OneToMany(cascade = CascadeType.ALL)
  private List<MenuItemPersistable> offerings = new ArrayList();

  public CoffeeShopPersistable(CoffeeShop domainEntity) {
    super(domainEntity);
  }

  public CoffeeShopPersistable() {
  }

  @Override
  public CoffeeShop toDomainEntity() {
    CoffeeShop cs = new CoffeeShop();
    cs.setId(String.valueOf(getId()));
    cs.setName(name);
    cs.setLongitude(longitude);
    cs.setLatitude(latitude);
    cs.setTelephoneNumber(telephoneNumber);
    cs.setServingRadius(servingRadius);
    cs.setTakingOrders(takingOrders);
    cs.setOfferings(offerings.parallelStream().map(MenuItemPersistable::toDomainEntity).collect(Collectors.toList()));
    return cs;

  }

  @Override
  protected CoffeeShopPersistable toPersistable(CoffeeShop domainEntity) {
    this.setId(NumberUtils.toLong(domainEntity.getId()));
    this.name = domainEntity.getName();
    this.longitude = domainEntity.getLongitude();
    this.latitude = domainEntity.getLatitude();
    this.telephoneNumber = domainEntity.getTelephoneNumber();
    this.servingRadius = domainEntity.getServingRadius();
    this.takingOrders = domainEntity.isTakingOrders();
    domainEntity.getOfferings().parallelStream().forEach(cnsmr -> {
      this.offerings.add(new MenuItemPersistable(cnsmr));
    });
    return this;
  }

}
