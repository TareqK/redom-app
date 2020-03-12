/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.repo.hibernate.vo;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import lombok.Data;
import me.kisoft.qahwagi.domain.core.entity.Customer;
import me.kisoft.qahwagi.infra.auth.repo.hibernate.vo.UserPersistable;
import me.kisoft.qahwagi.infra.repo.hibernate.vo.HibernatePersistable;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author tareq
 */
@Data
@Entity
@NamedQueries({
  @NamedQuery(name = "CustomerPersistable.byUserId", query = "SELECT cp FROM CustomerPersistable cp WHERE cp.user.id = (:user_id)")})
public class CustomerPersistable extends HibernatePersistable<Customer> {

  @OneToOne
  private UserPersistable user;

  public CustomerPersistable(Customer domainEntity) {
    super(domainEntity);
  }

  public CustomerPersistable() {
  }

  @Override
  public Customer toDomainEntity() {
    Customer c = new Customer();
    c.setId(String.valueOf(getId()));
    return c;
  }

  @Override
  protected CustomerPersistable toPersistable(Customer domainEntity) {
    this.setId(NumberUtils.toLong(domainEntity.getId()));
    this.user = new UserPersistable();
    this.user.setId(NumberUtils.toLong(domainEntity.getId()));
    return this;
  }

}
