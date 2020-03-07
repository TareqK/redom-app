/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.factory;

import me.kisoft.qahwagi.domain.core.service.BaristaService;
import me.kisoft.qahwagi.infra.core.service.impl.BaristaServiceImpl;
import me.kisoft.qahwagi.infra.factory.Factory;

/**
 *
 * @author tareq
 */
public class BaristaServiceFactory implements Factory<BaristaService> {

  private static BaristaServiceFactory instance = getInstance();

  private BaristaServiceFactory() {

  }

  public static BaristaServiceFactory getInstance() {
    if (instance == null) {
      instance = new BaristaServiceFactory();
    }
    return instance;
  }

  @Override
  public BaristaService get() {
    return new BaristaServiceImpl();
  }

}
