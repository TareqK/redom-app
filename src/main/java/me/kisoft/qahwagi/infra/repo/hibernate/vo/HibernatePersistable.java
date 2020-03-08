/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.repo.hibernate.vo;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.qahwagi.domain.entity.QahwagiEntity;
import me.kisoft.qahwagi.infra.vo.Transformable;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

/**
 *
 * @author tareq
 * @param <T>
 */
@Audited
@MappedSuperclass
public abstract class HibernatePersistable<T extends QahwagiEntity> implements Transformable<T> {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Getter
  @Setter
  private String id = null;

  public HibernatePersistable(T domainEntity) {
    this.fromDomainEntity(domainEntity);
  }

  public HibernatePersistable() {

  }

  @Override
  public final HibernatePersistable fromDomainEntity(T domainEntity) {
    this.toPersistable(domainEntity);
    return this;
  }

  protected abstract HibernatePersistable toPersistable(T domainEntity);

}
