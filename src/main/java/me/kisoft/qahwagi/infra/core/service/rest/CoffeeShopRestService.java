/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.rest;

import io.javalin.http.Context;
import java.util.List;
import me.kisoft.qahwagi.domain.core.entity.CoffeeShop;
import me.kisoft.qahwagi.infra.rest.service.RestService;

/**
 *
 * @author tareq
 */
public class CoffeeShopRestService extends RestService<CoffeeShop> {

  @Override
  public List<CoffeeShop> findAll() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public void updateMyShop(Context ctx) {

  }

  public void findNearest(Context ctx) {

  }

  @Override
  public CoffeeShop findOne(String id) {
    return null;
  }

  @Override
  public void save(CoffeeShop toSave) {
  }

  @Override
  public void update(CoffeeShop toUpdate, String id) {
  }

  @Override
  public void delete(String id) {
  }

  @Override
  public Class<CoffeeShop> getType() {
    return CoffeeShop.class;
  }

}
