/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.repo.hibernate.vo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import me.kisoft.qahwagi.domain.core.entity.Customer;
import me.kisoft.qahwagi.infra.vo.Transformable;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author tareq
 */
@Data
public class CustomerPersistable implements Transformable<Customer> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Override
  public Customer toDomainEntity() {
    Customer c = new Customer();
    c.setId(String.valueOf(id));
    return c;
  }

  @Override
  public CustomerPersistable fromDomainEntity(Customer domainEntity) {
    this.id = NumberUtils.toLong(domainEntity.getId());
    return this;
  }

}
