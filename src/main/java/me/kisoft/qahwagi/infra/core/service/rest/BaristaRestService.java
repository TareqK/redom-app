/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.rest;

import java.util.List;
import me.kisoft.qahwagi.domain.core.entity.Barista;
import me.kisoft.qahwagi.infra.rest.service.RestService;

/**
 *
 * @author tareq
 */
public class BaristaRestService extends RestService<Barista> {

  @Override
  public List<Barista> findAll() {
    return null;
  }

  @Override
  public Barista findOne(String id) {
    return null;
  }

  @Override
  public void save(Barista toSave) {
  }

  @Override
  public void update(Barista toUpdate, String id) {
  }

  @Override
  public void delete(String id) {
  }

  @Override
  public Class<Barista> getType() {
    return Barista.class;
  }

}
