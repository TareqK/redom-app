/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.repo.hibernate;

import java.util.List;
import me.kisoft.qahwagi.domain.core.entity.CoffeeShop;
import me.kisoft.qahwagi.domain.core.repo.CoffeeShopRepository;
import me.kisoft.qahwagi.infra.core.repo.hibernate.vo.CoffeeShopPersistable;
import me.kisoft.qahwagi.infra.repo.hiberante.HibernateCrudRepository;

/**
 *
 * @author tareq
 */
public class CoffeeShopRepositoryHibernateImpl extends HibernateCrudRepository<CoffeeShop, CoffeeShopPersistable> implements CoffeeShopRepository {

  @Override
  public Class<CoffeeShop> getType() {
    return CoffeeShop.class;
  }

  @Override
  public Class<CoffeeShopPersistable> getPersistable() {
    return CoffeeShopPersistable.class;
  }

  @Override
  public List<CoffeeShop> getServingShopsNear(double longitude, double latitude) {
    return this.findAll();
  }

}
