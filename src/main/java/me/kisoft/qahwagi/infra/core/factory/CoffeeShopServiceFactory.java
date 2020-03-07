/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.factory;

import me.kisoft.qahwagi.domain.core.service.CoffeeShopService;
import me.kisoft.qahwagi.infra.core.service.impl.CoffeeShopServiceImpl;
import me.kisoft.qahwagi.infra.factory.Factory;

/**
 *
 * @author tareq
 */
public class CoffeeShopServiceFactory implements Factory<CoffeeShopService> {

  private static CoffeeShopServiceFactory instance = getInstance();

  private CoffeeShopServiceFactory() {

  }

  public static CoffeeShopServiceFactory getInstance() {
    if (instance == null) {
      instance = new CoffeeShopServiceFactory();
    }
    return instance;
  }

  @Override
  public CoffeeShopService get() {
    return new CoffeeShopServiceImpl();
  }

}
