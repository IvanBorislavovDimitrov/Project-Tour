package app.config;

import app.entities.*;
import app.repostiories.HibernateRepository;
import app.repostiories.HibernateUtils;
import app.repostiories.base.GenericRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DIConfig {

    @Bean
    @Autowired
    public GenericRepository<Hotel> provideHotelGenericRepository(SessionFactory sessionFactory) {
        GenericRepository<Hotel> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Hotel.class);

        return repo;
    }

    @Bean
    @Autowired
    public GenericRepository<Destination> provideDestinationGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Destination> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Destination.class);

        return repo;
    }

    @Bean
    @Autowired
    public GenericRepository<Reservation> provideReservationGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Reservation> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Reservation.class);

        return repo;
    }

    @Bean
    @Autowired
    public GenericRepository<Role> provideRoleGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Role> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Role.class);

        return repo;
    }

    @Bean
    @Autowired
    public GenericRepository<User> provideUserGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<User> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(User.class);

        return repo;
    }


    @Bean
    public SessionFactory provideSessionFactory() {
        return HibernateUtils.getSessionFactory();
    }

}
