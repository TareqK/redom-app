/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.repo;

import java.util.List;
import me.kisoft.qahwagi.domain.entity.QahwagiEntity;

/**
 *
 * @author tareq
 */
public interface CrudRepository<T extends QahwagiEntity> extends AutoCloseable {

  List<T> findAll();

  T findById(String id);

  default T refresh(T toRefresh) {
    toRefresh = findById(toRefresh.getId());
    return toRefresh;
  }

  T save(T toSave);

  T update(T toUpdate, String id);

  public default T update(T toUpdate) {
    return this.update(toUpdate, toUpdate.getId());
  }

  void delete(String id);
}
