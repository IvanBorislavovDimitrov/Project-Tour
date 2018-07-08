package app.repostiories;

import java.util.List;

import app.entities.Hotel;
import app.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.*;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
    private static final SessionFactory sessionFactory;
    static {
        Configuration configuration = new Configuration().configure();

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Hotel.class);


        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

        serviceRegistryBuilder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutDown(){
        getSessionFactory().close();
    }

}
