package org.example.userservice;

import org.example.userservice.ui.ConsoleInterface;
import org.example.userservice.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConsoleInterface consoleInterface = new ConsoleInterface();
        consoleInterface.start();

        HibernateUtil.shutdown();
    }
}
