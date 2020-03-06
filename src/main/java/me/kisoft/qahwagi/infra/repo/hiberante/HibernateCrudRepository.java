/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.repo.hiberante;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import me.kisoft.qahwagi.domain.entity.QahwagiEntity;
import me.kisoft.qahwagi.infra.factory.EntityManagerFactory;
import me.kisoft.qahwagi.infra.repo.hibernate.vo.HibernatePersistable;
import me.kisoft.qahwagi.domain.repo.CrudRepository;

/**
 *
 * @author tareq
 */
public abstract class HibernateCrudRepository<T extends QahwagiEntity, P extends HibernatePersistable<T>> implements CrudRepository<T> {

  private EntityManager em = EntityManagerFactory.getInstance().get();

  protected EntityManager getEm() {
    return em;
  }

  @Override
  public List<T> findAll() {
    List<T> entities = new ArrayList<>();
    findAllPersistables().stream().forEach(persistable -> entities.add(persistable.toDomainEntity()));
    return entities;
  }

  private List<P> findAllPersistables() {
    CriteriaBuilder cb = getEm().getCriteriaBuilder();
    CriteriaQuery cq = cb.createQuery(getPersistable());
    Root rootEntry = cq.from(getPersistable());
    CriteriaQuery all = cq.select(rootEntry);

    TypedQuery allQuery = getEm().createQuery(all);
    return allQuery.getResultList();
  }

  @Override
  public T findById(String id) {
    P persistable = findPersistableById(id);
    if (persistable != null) {
      return persistable.toDomainEntity();
    }
    return null;
  }

  private P findPersistableById(String id) {
    return getEm().find(getPersistable(), id);
  }

  @Override
  public void save(T toSave) {
    P persistable = convertToPersistable(toSave);
    getEm().getTransaction().begin();
    try {
      if (persistable.getId() == null) {
        em.persist(persistable);
      } else {
        em.merge(persistable);
      }
    } finally {
      getEm().getTransaction().commit();
    }
  }

  @Override
  public void delete(String id) {
    P persistable = findPersistableById(id);
    if (persistable != null) {
      getEm().getTransaction().begin();
      try {
        if (persistable.getId() == null) {
          em.remove(persistable);
        }
      } finally {
        getEm().getTransaction().commit();
      }
    }
  }

  private P convertToPersistable(T toConvert) {
    try {
      return findTypeConstructor().newInstance(toConvert);
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
      Logger.getLogger(HibernateCrudRepository.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  private Constructor<P> findTypeConstructor() {
    try {
      return getPersistable().getConstructor(getType());
    } catch (NoSuchMethodException | SecurityException ex) {
      Logger.getLogger(HibernateCrudRepository.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  private Constructor<P> findNoArgsConstructor() {
    try {
      return getPersistable().getConstructor();
    } catch (NoSuchMethodException | SecurityException ex) {
      Logger.getLogger(HibernateCrudRepository.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  public abstract Class<T> getType();

  public abstract Class<P> getPersistable();

}
