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

  void save(T toSave);

  void update(T toUpdate, String id);

  public default void update(T toUpdate) {
    this.update(toUpdate, toUpdate.getId());
  }

  void delete(String id);
}
