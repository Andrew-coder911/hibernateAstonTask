package org.example.userservice.service;

import org.example.userservice.dao.UserDAO;

import java.time.LocalDateTime;
import java.util.List;

public class UserService {
    private final UserDAO userDao = new UserDAO();

    public void createUser(String name, String email, int age) {
        checkSameParams(name, email, age);

        UserDTO user = new UserDTO(name, email, age);
        userDao.createUser(user);
    }

    public void deleteUser(Long id) {
        if (id != null) {
            userDao.deleteUser(id);
        }
    }

    public UserDTO readUserById(Long id) {
        if (id != null) {
            return userDao.readUserById(id);
        } else {
            System.out.println("Пользователь по данному Id не найден.");
            return null;
        }
    }

    public List<UserDTO> readAllUsers() {
        return userDao.readAllUsers();
    }

    public void updateUser(Long id, String name, String email, int age) {
        if(id != null) {
            checkSameParams(name, email, age);
            UserDTO userDTO = new UserDTO(id, name, email, age);
            userDao.updateUser(userDTO);
        }
    }

    private static void checkSameParams(String name, String email, int age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email не может быть пустым");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным или равным нулю.");
        }
    }
}
