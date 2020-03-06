/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.repo.hibernate.vo;

import javax.persistence.Entity;
import lombok.Data;
import me.kisoft.qahwagi.domain.core.entity.Customer;
import me.kisoft.qahwagi.infra.repo.hibernate.vo.HibernatePersistable;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author tareq
 */
@Data
@Entity
public class CustomerPersistable extends HibernatePersistable<Customer> {

  @Override
  public Customer toDomainEntity() {
    Customer c = new Customer();
    c.setId(String.valueOf(getId()));
    return c;
  }

  @Override
  public CustomerPersistable fromDomainEntity(Customer domainEntity) {
    this.setId(NumberUtils.toLong(domainEntity.getId()));
    return this;
  }

}
