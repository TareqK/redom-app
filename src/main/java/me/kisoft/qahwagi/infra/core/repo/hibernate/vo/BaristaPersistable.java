/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.repo.hibernate.vo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.qahwagi.domain.core.entity.Barista;
import me.kisoft.qahwagi.infra.auth.repo.hibernate.vo.UserPersistable;
import me.kisoft.qahwagi.infra.repo.hibernate.vo.HibernatePersistable;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author tareq
 */
@Getter
@Setter
@Entity
@NamedQueries({
  @NamedQuery(name = "BaristaPersistable.byUserId", query = "SELECT bp FROM BaristaPersistable bp WHERE bp.user.id = (:user_id)")})
public class BaristaPersistable extends HibernatePersistable<Barista> implements Serializable {

  public BaristaPersistable(Barista domainEntity) {
    super(domainEntity);
  }

  public BaristaPersistable() {
  }

  @OneToOne(cascade = CascadeType.ALL)
  private CoffeeShopPersistable coffeeShop;

  @OneToOne
  private UserPersistable user;

  @Override
  public Barista toDomainEntity() {
    Barista b = new Barista();
    b.setId(user.getId());
    b.setCoffeeShop(coffeeShop.toDomainEntity());
    return b;
  }

  @Override
  protected BaristaPersistable toPersistable(Barista domainEntity) {
    this.user = new UserPersistable();
    this.user.setId(domainEntity.getId());
    this.coffeeShop = new CoffeeShopPersistable().toPersistable(domainEntity.getCoffeeShop());
    this.coffeeShop.setBarista(this);
    return this;
  }

}
