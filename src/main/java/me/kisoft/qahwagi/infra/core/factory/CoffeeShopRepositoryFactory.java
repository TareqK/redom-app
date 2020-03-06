/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.factory;

import me.kisoft.qahwagi.domain.core.repo.CoffeeShopRepository;
import me.kisoft.qahwagi.infra.core.repo.hibernate.CoffeeShopRepositoryHibernateImpl;
import me.kisoft.qahwagi.infra.factory.Factory;

/**
 *
 * @author tareq
 */
public class CoffeeShopRepositoryFactory implements Factory<CoffeeShopRepository> {

  private static CoffeeShopRepositoryFactory instance = getInstance();

  private CoffeeShopRepositoryFactory() {

  }

  public static final CoffeeShopRepositoryFactory getInstance() {
    if (instance == null) {
      instance = new CoffeeShopRepositoryFactory();
    }
    return instance;
  }

  @Override
  public CoffeeShopRepository get() {
    return new CoffeeShopRepositoryHibernateImpl();
  }

}
