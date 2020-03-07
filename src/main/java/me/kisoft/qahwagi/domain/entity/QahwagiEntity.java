/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.entity;

/**
 *
 * @author tareq
 */
public interface QahwagiEntity {

  String getId();

  void setId(String id);

  void postDeleted();

  void postSaved();

  void postUpdated();

}
