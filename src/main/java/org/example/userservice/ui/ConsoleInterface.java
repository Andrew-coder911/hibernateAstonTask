package org.example.userservice.ui;

import org.example.userservice.dao.UserDao;
import org.example.userservice.model.User;

import java.util.Scanner;

public class ConsoleInterface {
    Scanner scanner = new Scanner(System.in);
    UserDao userDao = new UserDao();

    public void start() {
        while (true) {
            System.out.println("Введите цифру необходимого действия:");
            System.out.println("1. Создать пользователя.");
            System.out.println("2. Получить пользователя по номеру (Id).");
            System.out.println("3. Обновить данные о пользователе.");
            System.out.println("4. Удалить пользователяю");
            System.out.println("5. Выход.");

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> createUser();
                case "2" -> getUserById();
                case "3" -> updateUserData();
                case "4" -> deleteUser();
                case "5" -> {
                    return;
                }
                default -> System.out.println("Повторите ввод.");
            }

        }
    }

    private void deleteUser() {
        System.out.println("Введите номер пользователя для удаления:");
        Long id = Long.parseLong(scanner.nextLine());
        userDao.deleteUser(id);
    }

    private void updateUserData() {
        System.out.println("Введите Id пользователя для замены:");
        Long id = Long.parseLong(scanner.nextLine());
        User user = userDao.getUserById(id);
        System.out.println("Введите новое имя:");
        user.setName(scanner.nextLine());
        System.out.println("Введите новый email:");
        user.setEmail(scanner.nextLine());
        System.out.println("Введите новый возраст:");
        user.setAge(scanner.nextInt());
        userDao.updateUser(user);
    }

    private void createUser() {
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите возраст:");
        int age = scanner.nextInt();

        User user = new User(name, email, age);
        userDao.saveUser(user);
        System.out.println("Пользователь добавлен в базу");
    }

    private void getUserById() {
        System.out.println("Введите номер пользователя в базе:");
        Long id = Long.parseLong(scanner.nextLine());
        User user = userDao.getUserById(id);
        if (user != null) {
            System.out.println(user.toString());
        } else {
            System.out.println("Пользователь не найден!");
        }
    }


}
