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
import me.kisoft.qahwagi.domain.core.entity.Barista;
import me.kisoft.qahwagi.infra.vo.Transformable;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author tareq
 */
@Data
public class BaristaPersistable implements Transformable<Barista> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private CoffeeShopPersistable coffeeShop = new CoffeeShopPersistable();

  @Override
  public Barista toDomainEntity() {
    Barista b = new Barista();
    b.setId(String.valueOf(id));
    b.setCoffeeShop(coffeeShop.toDomainEntity());
    return b;
  }

  @Override
  public BaristaPersistable fromDomainEntity(Barista domainEntity) {
    this.id = NumberUtils.toLong(domainEntity.getId());
    this.coffeeShop.fromDomainEntity(domainEntity.getCoffeeShop());
    return this;
  }

}
