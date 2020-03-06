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

  void postCreated();

  void postUpdated();

  void postDeleted();

}
