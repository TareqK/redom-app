/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.rest.service;

import io.javalin.http.Context;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author tareq
 */
public abstract class RestService<T> {

  public void getAll(@NotNull Context cntxt) {
    cntxt.json(findAll());
  }

  public void getOne(@NotNull Context cntxt) {
    cntxt.json(findOne(cntxt.pathParamMap().values().stream().findFirst().orElse(null)));
  }

  public void create(@NotNull Context cntxt) {
    T toSave = cntxt.bodyAsClass(getType());
    save(toSave);
    cntxt.json(toSave);
  }

  public void update(@NotNull Context cntxt) {
    T toUpdate = cntxt.bodyAsClass(getType());
    update(toUpdate, cntxt.pathParamMap().values().stream().findFirst().orElse(null));
    cntxt.json(toUpdate);
  }

  public void delete(@NotNull Context cntxt) {
    delete(cntxt.pathParamMap().values().stream().findFirst().orElse(null));
    cntxt.status(200);
  }

  public abstract List<T> findAll();

  public abstract T findOne(String id);

  public abstract void save(T toSave);

  public abstract void update(T toUpdate, String id);

  public abstract void delete(String id);

  public abstract Class<T> getType();

}
