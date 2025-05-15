package org.example.userservice.ui;

import org.example.userservice.service.UserDTO;
import org.example.userservice.service.UserService;

import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {
    private final Scanner scanner;
    private final UserService userService;

    public ConsoleInterface(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    public void start() {
        while (true) {
            System.out.println("Введите цифру необходимого действия:");
            System.out.println("1. Создать пользователя.");
            System.out.println("2. Получить пользователя по номеру (Id).");
            System.out.println("3. Обновить данные о пользователе.");
            System.out.println("4. Удалить пользователя");
            System.out.println("5. Вывести всех доступных пользователей.");
            System.out.println("6. Выход.");

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> createUser();
                case "2" -> readUserById();
                case "3" -> updateUserData();
                case "4" -> deleteUser();
                case "5" -> readAllUsers();
                case "6" -> {
                    return;
                }
                default -> System.out.println("Повторите ввод.");
            }

        }
    }

    private void createUser() {
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите возраст:");
        int age = Integer.parseInt(scanner.nextLine());

        userService.createUser(name, email, age);
        System.out.println("Пользователь добавлен в базу");
    }

    private void readUserById() {
        System.out.println("Введите номер пользователя в базе:");
        Long id = Long.parseLong(scanner.nextLine());
        UserDTO user = userService.readUserById(id);
        if (user != null) {
            System.out.println(user.toString());
        } else {
            System.out.println("Пользователь не найден!");
        }
    }

    private void updateUserData() {
        System.out.println("Введите Id пользователя для замены:");
        Long id = Long.parseLong(scanner.nextLine());
        userService.readUserById(id);
        System.out.println("Введите новое имя:");
        String name = scanner.nextLine();
        System.out.println("Введите новый email:");
        String email = scanner.nextLine();
        System.out.println("Введите новый возраст:");
        int age = Integer.parseInt(scanner.nextLine());

        userService.updateUserData(id, name, email, age);
    }

    private void deleteUser() {
        System.out.println("Введите номер пользователя для удаления:");
        Long id = Long.parseLong(scanner.nextLine());
        userService.deleteUser(id);
    }

    private void readAllUsers() {
        List<UserDTO> users = userService.readAllUsers();
        if (users.isEmpty()) {
            System.out.println("Записи в БД отсутствуют.");
        } else {
            for (UserDTO user : users) {
                System.out.println(user.toString());
            }
        }
    }
}
