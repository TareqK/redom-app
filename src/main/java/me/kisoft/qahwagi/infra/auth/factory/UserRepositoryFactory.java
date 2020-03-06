/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.auth.factory;

import me.kisoft.qahwagi.domain.auth.repo.UserRepository;
import me.kisoft.qahwagi.infra.auth.repo.hibernate.UserRepositoryHibernateImpl;
import me.kisoft.qahwagi.infra.factory.Factory;

/**
 *
 * @author tareq
 */
public class UserRepositoryFactory implements Factory<UserRepository> {

  private static UserRepositoryFactory instance = getInstance();

  private UserRepositoryFactory() {

  }

  public static final UserRepositoryFactory getInstance() {
    if (instance == null) {
      instance = new UserRepositoryFactory();
    }
    return instance;
  }

  @Override
  public UserRepository get() {
    return new UserRepositoryHibernateImpl();
  }

}
