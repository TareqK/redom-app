/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.auth.repo.hibernate.vo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import lombok.Data;
import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.auth.entity.UserRole;
import me.kisoft.qahwagi.infra.repo.hibernate.vo.HibernatePersistable;

/**
 *
 * @author tareq
 */
@Data
@Entity
@NamedQueries({
  @NamedQuery(name = "UserPersistable.byUsername", query = "SELECT up from UserPersistable up WHERE up.username = :username")
})
public class UserPersistable extends HibernatePersistable<User> implements Serializable {

  @Column(unique = true)
  private String username;
  private String password;
  @Enumerated(EnumType.STRING)
  private UserRole userRole;
  private String name;
  private String telephoneNumber;

  public UserPersistable(User domainEntity) {
    super(domainEntity);
  }

  public UserPersistable() {
  }

  @Override
  public User toDomainEntity() {
    User u = new User();
    u.setId(getId());
    u.setUsername(username);
    u.setPassword(password);
    u.setUserRole(userRole);
    u.setName(name);
    u.setTelephoneNumber(telephoneNumber);
    return u;
  }

  @Override
  public UserPersistable toPersistable(User domainEntity) {
    setId(domainEntity.getId());
    this.username = domainEntity.getUsername();
    this.password = domainEntity.getPassword();
    this.userRole = domainEntity.getUserRole();
    this.name = domainEntity.getName();
    this.telephoneNumber = domainEntity.getTelephoneNumber();
    return this;
  }

}
