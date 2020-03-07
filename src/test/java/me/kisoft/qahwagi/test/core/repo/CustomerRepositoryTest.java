/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.test.core.repo;

import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.auth.entity.UserRole;
import me.kisoft.qahwagi.domain.auth.repo.UserRepository;
import me.kisoft.qahwagi.domain.core.entity.Customer;
import me.kisoft.qahwagi.domain.core.repo.CustomerRepository;
import me.kisoft.qahwagi.infra.auth.factory.UserRepositoryFactory;
import me.kisoft.qahwagi.infra.core.factory.CustomerRepositoryFactory;
import me.kisoft.qahwagi.test.QahwagiTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author tareq
 */
public class CustomerRepositoryTest extends QahwagiTest {

  private final CustomerRepository customerRepo = CustomerRepositoryFactory.getInstance().get();
  private static User user;

  @BeforeClass
  public static void createTestUser() {
    user = new User();
    user.setUserRole(UserRole.ROLE_CUSTOMER);
    UserRepository repo = UserRepositoryFactory.getInstance().get();
    user = repo.save(user);
    assertNotNull(user.getId());
  }

  @Test
  public void createTest() {
    Customer c = customerRepo.findById(user.getId());
    assertEquals(c.getId(), user.getId());
  }
}
