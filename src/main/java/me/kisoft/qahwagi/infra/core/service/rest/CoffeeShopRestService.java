/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.rest;

import io.javalin.http.Context;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.core.entity.Barista;
import me.kisoft.qahwagi.domain.core.entity.CoffeeShop;
import me.kisoft.qahwagi.domain.core.repo.BaristaRepository;
import me.kisoft.qahwagi.domain.core.repo.CoffeeShopRepository;
import me.kisoft.qahwagi.infra.core.factory.BaristaRepositoryFactory;
import me.kisoft.qahwagi.infra.core.factory.CoffeeShopRepositoryFactory;
import me.kisoft.qahwagi.infra.rest.service.RestService;

/**
 *
 * @author tareq
 */
public class CoffeeShopRestService extends RestService<CoffeeShop> {

  @Override
  public List<CoffeeShop> findAll() throws Exception {
    try (CoffeeShopRepository repo = CoffeeShopRepositoryFactory.getInstance().get()) {
      return repo.findAll();
    }
  }

  @Override
  public CoffeeShop findOne(String id) throws Exception {
    try (CoffeeShopRepository repo = CoffeeShopRepositoryFactory.getInstance().get()) {
      return repo.findById(id);
    }
  }

  @Override
  public void save(CoffeeShop toSave) throws Exception {
    try (CoffeeShopRepository repo = CoffeeShopRepositoryFactory.getInstance().get()) {
      repo.save(toSave);
    }
  }

  @Override
  public void update(CoffeeShop toUpdate, String id) throws Exception {
    try (CoffeeShopRepository repo = CoffeeShopRepositoryFactory.getInstance().get()) {
      repo.update(toUpdate, id);
    }
  }

  @Override
  public void delete(String id) throws Exception {
    try (CoffeeShopRepository repo = CoffeeShopRepositoryFactory.getInstance().get()) {
      repo.delete(id);
    }
  }

  public void findNearest(Context ctx) {
    try (CoffeeShopRepository repo = CoffeeShopRepositoryFactory.getInstance().get()) {
      ctx.json(repo.getServingShopsNear(ctx.queryParam("latitude", Long.class).get(), ctx.queryParam("longitude", Long.class).get()));
    } catch (Exception ex) {
      ctx.res.setStatus(500);
      Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public Class<CoffeeShop> getType() {
    return CoffeeShop.class;
  }

  public void getMyShop(Context ctx) {
    try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
      User u = ctx.sessionAttribute("user");
      Barista b = repo.findById(u.getId());
      ctx.json(b.getCoffeeShop());
    } catch (Exception ex) {
      ctx.res.setStatus(500);
      Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void updateMyShop(Context ctx) {
    try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
      User u = ctx.sessionAttribute("user");
      Barista b = repo.findById(u.getId());
      CoffeeShop shop = ctx.bodyAsClass(CoffeeShop.class);
      shop.setId(b.getCoffeeShop().getId());
      b.setCoffeeShop(shop);
      repo.update(b);
    } catch (Exception ex) {
      ctx.res.setStatus(500);
      Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
