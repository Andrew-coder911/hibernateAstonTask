package org.example.userservice;

import org.example.userservice.dao.UserDao;
import org.example.userservice.model.User;
import org.example.userservice.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setName("testUser");
        user.setEmail("test@example.com");
        userDao.saveUser(user);

        HibernateUtil.shutdown();
    }
}
