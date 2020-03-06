/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.repo.hibernate;

import me.kisoft.qahwagi.domain.core.entity.Barista;
import me.kisoft.qahwagi.domain.core.repo.BaristaRepository;
import me.kisoft.qahwagi.infra.core.repo.hibernate.vo.BaristaPersistable;
import me.kisoft.qahwagi.infra.repo.hiberante.HibernateCrudRepository;

/**
 *
 * @author tareq
 */
public class BaristaRepositoryHibernateImpl extends HibernateCrudRepository<Barista, BaristaPersistable> implements BaristaRepository {

  @Override
  public Class<Barista> getType() {
    return Barista.class;
  }

  @Override
  public Class<BaristaPersistable> getPersistable() {
    return BaristaPersistable.class;
  }

}
