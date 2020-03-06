/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.repo.hibernate.vo;

import javax.persistence.Entity;
import lombok.Data;
import me.kisoft.qahwagi.domain.core.entity.Barista;
import me.kisoft.qahwagi.infra.repo.hibernate.vo.HibernatePersistable;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author tareq
 */
@Data
@Entity
public class BaristaPersistable extends HibernatePersistable<Barista> {

  private CoffeeShopPersistable coffeeShop = new CoffeeShopPersistable();

  @Override
  public Barista toDomainEntity() {
    Barista b = new Barista();
    b.setId(String.valueOf(getId()));
    b.setCoffeeShop(coffeeShop.toDomainEntity());
    return b;
  }

  @Override
  public BaristaPersistable fromDomainEntity(Barista domainEntity) {
    this.setId(NumberUtils.toLong(domainEntity.getId()));
    this.coffeeShop.fromDomainEntity(domainEntity.getCoffeeShop());
    return this;
  }

}
