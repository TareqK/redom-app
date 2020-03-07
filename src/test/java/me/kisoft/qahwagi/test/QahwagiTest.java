/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.kisoft.qahwagi.app.Main;
import me.kisoft.qahwagi.domain.event.EventBus;
import me.kisoft.qahwagi.infra.factory.EntityManagerFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 * @author tareq
 */
public class QahwagiTest {

  @BeforeClass
  public static final void initTest() {
    EntityManagerFactory.getInstance().setPersistenceUnit("qahwagi_test_PU");
    EventBus.getInstance().searchForHandlers();
  }

  @AfterClass
  public static final void destroyTest() {
    EventBus.getInstance().removeHandlers();
    try {
      String jdbc = String.valueOf(EntityManagerFactory.getInstance().get().getEntityManagerFactory().getProperties().get("javax.persistence.jdbc.url"));
      DriverManager.getConnection(jdbc + ";shutdown=true");
    } catch (SQLException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
