package app.repostiories;

import app.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.security.auth.login.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    static {
        // Problem
        Configuration configuration =

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Destination.class);
        configuration.addAnnotatedClass(Reservation.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(Hotel.class);

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

        serviceRegistryBuilder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
