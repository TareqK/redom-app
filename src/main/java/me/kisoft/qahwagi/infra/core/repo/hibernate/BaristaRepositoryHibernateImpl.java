/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.repo.hibernate;

import javax.persistence.NoResultException;
import me.kisoft.qahwagi.domain.core.entity.Barista;
import me.kisoft.qahwagi.domain.core.repo.BaristaRepository;
import me.kisoft.qahwagi.infra.core.repo.hibernate.vo.BaristaPersistable;
import me.kisoft.qahwagi.infra.repo.hiberante.HibernateCrudRepository;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author tareq
 */
public class BaristaRepositoryHibernateImpl extends HibernateCrudRepository<Barista, BaristaPersistable> implements BaristaRepository {

  @Override
  public Barista findById(String id) {
    BaristaPersistable foundBarista = getPersistableByUserId(id);
    if (foundBarista != null) {
      return foundBarista.toDomainEntity();
    }
    return null;

  }

  private BaristaPersistable getPersistableByUserId(String userId) {
    try {
      return getEm().createNamedQuery("BaristaPersistable.byUserId", BaristaPersistable.class)
       .setParameter("user_id", NumberUtils.toLong(userId))
       .getSingleResult();
    } catch (NoResultException ex) {
      return null;
    }
  }

  @Override
  public Barista update(Barista toUpdate, String id) {
    toUpdate.setId(id);
    BaristaPersistable foundBarista = getPersistableByUserId(id);
    if (foundBarista != null) {
      BaristaPersistable updatedBarista = new BaristaPersistable(toUpdate);
      updatedBarista.setId(foundBarista.getId());
      getEm().getTransaction().begin();
      try {
        getEm().merge(updatedBarista);
      } finally {
        getEm().getTransaction().commit();
      }
      toUpdate = updatedBarista.toDomainEntity();
      toUpdate.postUpdated();
    }
    return toUpdate;
  }

  @Override
  public void delete(String id) {
    BaristaPersistable foundBarista = getPersistableByUserId(id);
    if (foundBarista != null) {
      getEm().getTransaction().begin();
      try {
        getEm().remove(foundBarista);
      } finally {
        getEm().getTransaction().commit();
      }
      foundBarista.toDomainEntity().postDeleted();
    }
  }

  @Override
  public Class<Barista> getType() {
    return Barista.class;
  }

  @Override
  public Class<BaristaPersistable> getPersistable() {
    return BaristaPersistable.class;
  }

}
