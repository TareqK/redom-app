/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.test.core.repo;

import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.auth.entity.UserRole;
import me.kisoft.qahwagi.domain.auth.repo.UserRepository;
import me.kisoft.qahwagi.domain.core.entity.Barista;
import me.kisoft.qahwagi.domain.core.repo.BaristaRepository;
import me.kisoft.qahwagi.infra.auth.factory.UserRepositoryFactory;
import me.kisoft.qahwagi.infra.core.factory.BaristaRepositoryFactory;
import me.kisoft.qahwagi.test.QahwagiTest;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author tareq
 */
public class BaristaRepositoryTest extends QahwagiTest {

  private final BaristaRepository customerRepo = BaristaRepositoryFactory.getInstance().get();
  private static User user;

  @BeforeClass
  public static void createTestUser() {
    user = new User();
    user.setUserRole(UserRole.ROLE_CUSTOMER);
    UserRepository repo = UserRepositoryFactory.getInstance().get();
    user = repo.save(user);
  }

  @Test
  public void createTest() {
    Barista c = customerRepo.findById(user.getId());
    assertEquals(c.getId(), user.getId());
  }

}
