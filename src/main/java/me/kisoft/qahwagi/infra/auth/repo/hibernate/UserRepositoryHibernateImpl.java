/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.auth.repo.hibernate;

import javax.persistence.NoResultException;
import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.auth.repo.UserRepository;
import me.kisoft.qahwagi.infra.auth.repo.hibernate.vo.UserPersistable;
import me.kisoft.qahwagi.infra.repo.hiberante.HibernateCrudRepository;

/**
 *
 * @author tareq
 */
public class UserRepositoryHibernateImpl extends HibernateCrudRepository<User, UserPersistable> implements UserRepository {

  @Override
  public User getUserByUsername(String username) {
    try {
      return getEm().createNamedQuery("UserPersistable.byUsername", UserPersistable.class)
       .setParameter("username", username)
       .getSingleResult().toDomainEntity();
    } catch (NoResultException ex) {
      return null;
    }
  }

  @Override
  public Class<User> getType() {
    return User.class;
  }

  @Override
  public Class<UserPersistable> getPersistable() {
    return UserPersistable.class;
  }

}
