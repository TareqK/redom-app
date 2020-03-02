/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.repo;

import java.util.List;

/**
 *
 * @author tareq
 */
public interface CrudRepository<T> {

  List<T> findAll();

  T findById(String id);

  void save(T toSave);

  void delete(String id);
}
