/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.rest.service;

import io.javalin.http.Context;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author tareq
 */
public abstract class RestService<T> {

  public void getAll(@NotNull Context ctx) {
    try {
      ctx.json(findAll());
    } catch (Exception ex) {
      ctx.res.setStatus(500);
      Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void getOne(@NotNull Context ctx) {
    try {
      ctx.json(findOne(ctx.pathParamMap().values().stream().findFirst().orElse(null)));
    } catch (Exception ex) {
      ctx.res.setStatus(500);
      Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void create(@NotNull Context ctx) {
    try {
      T toSave = ctx.bodyAsClass(getType());
      save(toSave);
      ctx.json(toSave);
    } catch (Exception ex) {
      ctx.res.setStatus(500);
      Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void update(@NotNull Context ctx) {
    try {
      T toUpdate = ctx.bodyAsClass(getType());
      update(toUpdate, ctx.pathParamMap().values().stream().findFirst().orElse(null));
      ctx.json(toUpdate);
    } catch (Exception ex) {
      ctx.res.setStatus(500);
      Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void delete(@NotNull Context ctx) {
    try {
      delete(ctx.pathParamMap().values().stream().findFirst().orElse(null));
    } catch (Exception ex) {
      ctx.res.setStatus(500);
      Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
    }
    ctx.status(200);
  }

  public abstract List<T> findAll() throws Exception;

  public abstract T findOne(String id) throws Exception;

  public abstract void save(T toSave) throws Exception;

  public abstract void update(T toUpdate, String id) throws Exception;

  public abstract void delete(String id) throws Exception;

  public abstract Class<T> getType();

}
