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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id = null;

  public void setId(Long id) {
    this.id = id;
    if (id == null || id == 0L) {
      this.id = null;
    }
  }

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
