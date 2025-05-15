package org.example.userservice.service;


import org.example.userservice.dao.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserDAO userDAO;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreateUserValidData() {
        String name = "Evgeny Smirnov";
        String email = "evgeny@mail.ru";
        int age = 20;

        userService.createUser(name, email, age);

        Mockito.verify(userDAO);
    }
}