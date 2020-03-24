/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import lombok.extern.java.Log;
import me.kisoft.qahwagi.app.Qahwagi;
import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.auth.entity.UserRole;
import me.kisoft.qahwagi.domain.event.EventBus;
import me.kisoft.qahwagi.infra.auth.service.rest.vo.SignInRequest;
import me.kisoft.qahwagi.infra.factory.EntityManagerFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 * @author tareq
 */
@Log
public abstract class QahwagiTest {

    @BeforeClass
    public static final void initTest() {
        EventBus.getInstance().searchForHandlers();
        Qahwagi.startServer();
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.FINEST);
        EntityManagerFactory.getInstance().setPersistenceUnit("qahwagi_test_PU");

        RestAssured.port = 7000;
        RestAssured.baseURI = "http://localhost";
        createTestUsers();
    }

    @AfterClass
    public static final void destroyTest() {
        EventBus.getInstance().removeHandlers();
        try {
            String jdbc = String.valueOf(EntityManagerFactory.getInstance().get().getEntityManagerFactory().getProperties().get("javax.persistence.jdbc.url"));
            DriverManager.getConnection(jdbc + ";shutdown=true");
        } catch (SQLException ex) {
            log.info("Database Shutdown");
        } finally {
            Qahwagi.stopServer();
        }
    }

    public static void createTestUsers() {
        createBaristaUser("barista", "barista");
        createCustomerUser("customer", "customer");
    }

    public static void createBaristaUser(String username, String password) {
        createUser(username, password, UserRole.ROLE_BARISTA);
        System.out.println("Created Barista Test User");
    }

    public static void createCustomerUser(String username, String password) {
        createUser(username, password, UserRole.ROLE_CUSTOMER);
        System.out.println("Created Customer Test User");
    }

    public static void createUser(String username, String password, UserRole role) {
        User toCreate = new User();
        toCreate.setUserRole(role);
        toCreate.setUsername(username);
        toCreate.setPassword(password);
        given().when().body(toCreate).post("/user/signup").then().statusCode(200);
    }

    public static RequestSpecification asCustomer() {
        SignInRequest request = new SignInRequest();
        request.setUsername("customer");
        request.setPassword("customer");
        Response post = given().when().body(request).post("/user/signin");
        post.then().statusCode(200);
        return given().cookies(post.getCookies());
    }

    public static RequestSpecification asBarista() {
        SignInRequest request = new SignInRequest();
        request.setUsername("barista");
        request.setPassword("barista");
        Response post = given().when().body(request).post("/user/signin");
        post.then().statusCode(200);
        return given().cookies(post.getCookies());
    }
}
