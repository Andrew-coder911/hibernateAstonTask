package org.example.userservice.service;

import org.example.userservice.dao.UserDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @Captor
    ArgumentCaptor<UserDTO> userDataCaptor;

    @Test
    void createUserTest() {
        String name = "Ivan";
        String email = "ivanov@mail.ru";
        int age = 44;
        UserDTO user = new UserDTO(name, email, age);

        userService.createUser(name, email, age);

        verify(userDAO).createUser(user);
    }

    @Test
    void createUserWithEmptyNameThrowsExceptionTest() {
        String name = " ";
        String email = "ivanov@mail.ru";
        int age = 44;

        assertThrows(IllegalArgumentException.class,
                () -> userService.createUser(name, email, age));
    }

    @Test
    void createUserWithEmptyEmailThrowsExceptionTest() {
        String name = "Ivanov";
        String email = " ";
        int age = 44;

        assertThrows(IllegalArgumentException.class,
                () -> userService.createUser(name, email, age));
    }

    @Test
    void createUserNotCorrectAgeThrowsExceptionTest() {
        String name = "Ivanov";
        String email = "ivanov@mail.ru";
        int age = -1;

        assertThrows(IllegalArgumentException.class,
                () -> userService.createUser(name, email, age));
    }

    @Test
    void readUserByIdTest() {
        Long id = 1L;
        UserDTO expected = new UserDTO(id, "Ivan", "ivanov@mail.ru" , 44);
        when(userDAO.readUserById(id)).thenReturn(expected);

        UserDTO actual = userService.readUserById(id);

        assertEquals(expected, actual);
        verify(userDAO).readUserById(1L);
    }

    @Test
    void readUserByIdWhenIdNull() {
        Long id = null;

        assertNull(userService.readUserById(id));
        verify(userDAO, never()).readUserById(any());
    }

    @Test
    void updateUserDataTest() {
        Long id = 2L;
        String name = "Ivan";
        String email = "ivanov@mail.ru";
        int age = 44;

        userService.updateUserData(id, name, email, age);

        verify(userDAO).updateUserData(userDataCaptor.capture());
        UserDTO capturedUserData = userDataCaptor.getValue();

        assertAll(
                () -> assertEquals(id, capturedUserData.getId()),
                () -> assertEquals(name, capturedUserData.getName()),
                () -> assertEquals(email, capturedUserData.getEmail()),
                () -> assertEquals(age, capturedUserData.getAge())
        );
    }

    @Test
    void updateUserDataIfIdNullTest() {
        userService.updateUserData(null, "Name", "email", 44);
        verify(userDAO, never()).updateUserData(any());
    }


}