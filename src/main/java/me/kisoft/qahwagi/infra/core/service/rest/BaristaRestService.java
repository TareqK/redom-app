/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.rest;

import java.util.List;
import me.kisoft.qahwagi.domain.core.entity.Barista;
import me.kisoft.qahwagi.domain.core.repo.BaristaRepository;
import me.kisoft.qahwagi.infra.core.factory.BaristaRepositoryFactory;
import me.kisoft.qahwagi.infra.rest.service.RestService;

/**
 *
 * @author tareq
 */
public class BaristaRestService extends RestService<Barista> {

  @Override
  public List<Barista> findAll() throws Exception {
    try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
      return repo.findAll();
    }
  }

  @Override
  public Barista findOne(String id) throws Exception {
    try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
      return repo.findById(id);
    }
  }

  @Override
  public void save(Barista toSave) throws Exception {
    try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
      repo.save(toSave);
    }
  }

  @Override
  public void update(Barista toUpdate, String id) throws Exception {
    try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
      repo.update(toUpdate, id);
    }
  }

  @Override
  public void delete(String id) throws Exception {
    try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
      repo.delete(id);
    }
  }

  @Override
  public Class<Barista> getType() {
    return Barista.class;
  }

}
