package app.repostiories;

import app.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtils {
    private static SessionFactory sessionFactory;

    static {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration()
                .configure();

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Reservation.class);
        configuration.addAnnotatedClass(Privilege.class);
        configuration.addAnnotatedClass(Hotel.class);
        configuration.addAnnotatedClass(Room.class);
        configuration.addAnnotatedClass(TourGuide.class);

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

        serviceRegistryBuilder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
