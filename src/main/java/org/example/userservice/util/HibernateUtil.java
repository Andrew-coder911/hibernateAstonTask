package org.example.userservice.util;

import org.example.userservice.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration cfg = new Configuration()
                    .configure("hibernate.cfg.xml");
            return cfg.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory initialization failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown(){
        getSessionFactory().close();
    }
}
