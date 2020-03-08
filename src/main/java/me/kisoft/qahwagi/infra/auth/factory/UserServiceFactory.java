/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.auth.factory;

import me.kisoft.qahwagi.domain.auth.service.UserService;
import me.kisoft.qahwagi.infra.auth.service.impl.UserServiceImpl;
import me.kisoft.qahwagi.infra.factory.Factory;

/**
 *
 * @author tareq
 */
public class UserServiceFactory implements Factory<UserService> {

  private static UserServiceFactory instance = getInstance();

  private UserServiceFactory() {

  }

  public static UserServiceFactory getInstance() {
    if (instance == null) {
      instance = new UserServiceFactory();
    }
    return instance;
  }

  @Override
  public UserService get() {
    return new UserServiceImpl();
  }

}
