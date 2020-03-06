/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.vo;

import me.kisoft.qahwagi.domain.entity.QahwagiEntity;

/**
 *
 * @author tareq
 */
public interface Transformable<T extends QahwagiEntity> {

  public T toDomainEntity();

  public Transformable fromDomainEntity(T domainEntity);

}
