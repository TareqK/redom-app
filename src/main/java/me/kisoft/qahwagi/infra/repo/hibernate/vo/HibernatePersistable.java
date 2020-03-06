/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.repo.hibernate.vo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.qahwagi.domain.entity.QahwagiEntity;
import me.kisoft.qahwagi.infra.vo.Transformable;
import org.hibernate.envers.Audited;

/**
 *
 * @author tareq
 * @param <T>
 */
@Audited
@MappedSuperclass
public abstract class HibernatePersistable<T extends QahwagiEntity> implements Transformable<T> {

  private transient T domainEntity;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  @Setter
  private Long id;

  public HibernatePersistable(T domainEntity) {
    this.domainEntity = domainEntity;
    this.fromDomainEntity(domainEntity);
  }

  public HibernatePersistable() {

  }

  @PostPersist
  public void postPersist() {
    if (domainEntity != null) {
      domainEntity.postCreated();
    }
  }

  @PostUpdate
  public void postUpdate() {
    if (domainEntity != null) {
      domainEntity.postUpdated();
    }
  }

  @PostRemove
  public void postDelete() {
    if (domainEntity != null) {
      domainEntity.postDeleted();
    }
  }

}
