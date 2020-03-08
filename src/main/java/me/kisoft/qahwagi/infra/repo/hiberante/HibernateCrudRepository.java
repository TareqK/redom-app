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
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.TransactionManager;
import lombok.SneakyThrows;
import me.kisoft.qahwagi.domain.entity.QahwagiEntity;
import me.kisoft.qahwagi.domain.repo.CrudRepository;
import me.kisoft.qahwagi.infra.factory.EntityManagerFactory;
import me.kisoft.qahwagi.infra.repo.hibernate.vo.HibernatePersistable;
import me.kisoft.qahwagi.infra.vo.Transformable;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tareq
 */
public abstract class HibernateCrudRepository<T extends QahwagiEntity, P extends HibernatePersistable<T>> implements CrudRepository<T>, AutoCloseable {

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
    try {
      return getEm().find(getPersistable(), id);
    } catch (NoResultException ex) {
      return null;
    }
  }

  @SneakyThrows
  @Override
  public T save(T toSave) {
    P persistable = convertToPersistable(toSave);
    TransactionManager manager = getTransactionManager();
    manager.begin();
    try {
      if (StringUtils.isBlank(toSave.getId())) {
        getEm().persist(persistable);
      } else {
        persistable = getEm().merge(persistable);
      }
    } finally {
      manager.commit();
    }
    T newEntity = (T) ((Transformable) persistable).toDomainEntity();
    toSave.setId(newEntity.getId());
    newEntity.postSaved();
    return newEntity;
  }

  @SneakyThrows
  @Override
  public T update(T toUpdate, String id) {
    toUpdate.setId(id);
    P persistable = convertToPersistable(toUpdate);
    TransactionManager manager = getTransactionManager();
    manager.begin();
    try {
      persistable = getEm().merge(persistable);
    } finally {
      manager.commit();
    }
    T newEntity = (T) ((Transformable) persistable).toDomainEntity();
    toUpdate.setId(newEntity.getId());
    newEntity.postSaved();
    return newEntity;
  }

  @SneakyThrows
  @Override
  public void delete(String id) {
    P persistable = findPersistableById(id);
    if (persistable != null) {
      TransactionManager manager = getTransactionManager();
      manager.begin();
      try {
        if (persistable.getId() == null) {
          getEm().remove(persistable);
        }
      } finally {
        manager.commit();
      }
      ((Transformable) persistable).toDomainEntity().postDeleted();
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

  @Override
  public void close() {
    getEm().close();
  }

  public TransactionManager getTransactionManager() {
    return com.arjuna.ats.jta.TransactionManager.transactionManager();
  }

  public abstract Class<T> getType();

  public abstract Class<P> getPersistable();

}
