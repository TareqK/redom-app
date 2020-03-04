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
import me.kisoft.qahwagi.domain.core.entity.CoffeeShop;
import me.kisoft.qahwagi.infra.vo.Transformable;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author tareq
 */
@Data
public class CoffeeShopPersistable implements Transformable<CoffeeShop> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private double longitude;
  private double latitude;
  private String telephoneNumber;
  private double servingRadius;
  private boolean takingOrders;
  private List<MenuItemPersistable> offerings = new ArrayList();

  @Override
  public CoffeeShop toDomainEntity() {
    CoffeeShop cs = new CoffeeShop();
    cs.setId(String.valueOf(id));
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
  public CoffeeShopPersistable fromDomainEntity(CoffeeShop domainEntity) {
    this.id = NumberUtils.toLong(domainEntity.getId());
    this.name = domainEntity.getName();
    this.longitude = domainEntity.getLongitude();
    this.latitude = domainEntity.getLatitude();
    this.telephoneNumber = domainEntity.getTelephoneNumber();
    this.servingRadius = domainEntity.getServingRadius();
    this.takingOrders = domainEntity.isTakingOrders();
    domainEntity.getOfferings().parallelStream().forEach(cnsmr -> {
      this.offerings.add(new MenuItemPersistable().fromDomainEntity(cnsmr));
    });
    return this;
  }

}
