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

    @Bean(name = "Hotel")
    @Autowired
    public GenericRepository<Hotel> provideHotelGenericRepository(SessionFactory sessionFactory) {
        GenericRepository<Hotel> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Hotel.class);

        return repo;
    }

    @Bean(name = "Reservation")
    @Autowired
    public GenericRepository<Reservation> provideReservationGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Reservation> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Reservation.class);

        return repo;
    }

    @Bean(name = "Privilege")
    @Autowired
    public GenericRepository<Privilege> provideRoleGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Privilege> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Privilege.class);

        return repo;
    }

    @Bean(name = "User")
    @Autowired
    public GenericRepository<User> provideUserGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<User> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(User.class);

        return repo;
    }

    @Bean(name = "Room")
    @Autowired
    public GenericRepository<Room> provideRoomGenericRepository(SessionFactory sessionFactory) {
        GenericRepository<Room> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Room.class);

        return repo;
    }

    @Bean(name = "TourGuide")
    @Autowired
    public GenericRepository<TourGuide> provideGuidesGenericRepository(SessionFactory sessionFactory) {
        GenericRepository<TourGuide> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(TourGuide.class);

        return repo;
    }

    @Bean
    public SessionFactory provideSessionFactory() {
        return HibernateUtils.getSessionFactory();
    }

}
