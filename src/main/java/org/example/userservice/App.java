package org.example.userservice;

import org.example.userservice.dao.UserDAO;
import org.example.userservice.service.UserService;
import org.example.userservice.ui.ConsoleInterface;
import org.example.userservice.util.HibernateUtil;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserDAO userDAO = new UserDAO();
        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService(userDAO);

        ConsoleInterface ui = new ConsoleInterface(scanner, userService);
        ui.start();

        HibernateUtil.shutdown();
    }
}
