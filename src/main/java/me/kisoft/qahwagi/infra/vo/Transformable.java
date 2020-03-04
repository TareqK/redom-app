/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.vo;

/**
 *
 * @author tareq
 */
public interface Transformable<T> {

  public T toDomainEntity();

  public Transformable fromDomainEntity(T domainEntity);

}
