package org.example.userservice.dao;

import org.example.userservice.service.UserDTO;
import org.example.userservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
            .withPassword("testpassword")
            .withInitScript("schema.sql");

    private static UserDAO userDAO;

    //Настройка Hibernate для использования Testcontainers
    @BeforeAll
    static void setUp() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        configuration.setProperty("hibernate.connection.username", postgres.getUsername());
        configuration.setProperty("hibernate.connection.password", postgres.getPassword());
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
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

    @BeforeEach
    void clearDatabase() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM UserEntity").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    void createUserTest() {
        UserDTO userDTO = new UserDTO("John Doe", "john@example.com", 30);
        userDAO.createUser(userDTO);

        // Получаем ID созданного пользователя
        Long userId = userDAO.readAllUsers().get(0).getId();
        UserDTO savedUser = userDAO.readUserById(userId);

        assertNotNull(savedUser);
        assertEquals("John Doe", savedUser.getName());
        assertEquals("john@example.com", savedUser.getEmail());
        assertEquals(30, savedUser.getAge());
    }

    @Test
    void updateUserDataTest() {
        UserDTO userDTO = new UserDTO("John Doe", "john@example.com", 30);
        userDAO.createUser(userDTO);

        Long userID = userDAO.readAllUsers().get(0).getId();
        UserDTO updatedUserDTO = new UserDTO(userID, "NewJohn Doe", "john.new@example.com", 44);
        userDAO.updateUserData(updatedUserDTO);
        UserDTO receivedUser = userDAO.readUserById(userID);

        assertNotNull(receivedUser, "Пользователь не обновлен.");
        assertEquals(userID, receivedUser.getId(), "Неверный Id пользователя.");
        assertEquals("NewJohn Doe", receivedUser.getName());
        assertEquals("john.new@example.com", receivedUser.getEmail());
        assertEquals(44, receivedUser.getAge());
    }

    @Test
    void deleteUserTest() {
        UserDTO userDTO = new UserDTO("John Doe", "john@example.com", 30);
        userDAO.createUser(userDTO);

        Long userId = userDAO.readAllUsers().get(0).getId();
        userDAO.deleteUser(userId);
        UserDTO receivedUser = userDAO.readUserById(userId);

        assertNull(receivedUser, "Пользователь не удален из БД.");
    }
}