/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.factory;

import me.kisoft.qahwagi.domain.core.repo.BaristaRepository;
import me.kisoft.qahwagi.infra.core.repo.hibernate.BaristaRepositoryHibernateImpl;
import me.kisoft.qahwagi.infra.factory.Factory;

/**
 *
 * @author tareq
 */
public class BaristaRepositoryFactory implements Factory<BaristaRepository> {

  private static BaristaRepositoryFactory instance = getInstance();

  private BaristaRepositoryFactory() {

  }

  public static final BaristaRepositoryFactory getInstance() {
    if (instance == null) {
      instance = new BaristaRepositoryFactory();
    }
    return instance;
  }

  @Override
  public BaristaRepository get() {
    return new BaristaRepositoryHibernateImpl();
  }

}
