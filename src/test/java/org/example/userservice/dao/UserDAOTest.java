package org.example.userservice.dao;

import org.example.userservice.service.UserDTO;
import org.example.userservice.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
class UserDAOTest {
    //Создание контейнера postgresSQL
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpassword");
//            .withInitScript("schema.sql");

    private static UserDAO userDAO;

    //Настройка Hibernate для использования Testcontainers
    @BeforeAll
    static void setUp() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        configuration.setProperty("hibernate.connection.username", postgres.getUsername());
        configuration.setProperty("hibernate.connection.password", postgres.getPassword());
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        configuration.addAnnotatedClass(UserEntity.class);

        //Создаем SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        HibernateUtil.setSessionFactory(sessionFactory);

        userDAO = new UserDAO();
    }

    @AfterAll
    static void shutdown() {
        HibernateUtil.shutdown();
    }

    @Test
    void createUserTest() {
        UserDTO userDTO = new UserDTO(1L, "John Doe", "john@example.com", 30);

        userDAO.createUser(userDTO);

        UserDTO savedUser = userDAO.readUserById(1L);
        assertNotNull(savedUser);
        assertEquals("John Doe", savedUser.getName());
        assertEquals("john@example.com", savedUser.getEmail());
        assertEquals(30, savedUser.getAge());
    }
}