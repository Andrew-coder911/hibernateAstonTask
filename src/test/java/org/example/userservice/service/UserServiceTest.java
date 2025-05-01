package org.example.userservice.service;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @org.junit.jupiter.api.Test
    void createUser() {
        UserService userService = new UserService();
        userService.createUser("Andy", "sldhs@ndslkn.ru", 18);

    }
}