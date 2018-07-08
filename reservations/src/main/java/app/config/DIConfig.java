package app.config;

import app.entities.Hotel;
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
    GenericRepository<Hotel> provideGenericRepository(SessionFactory sessionFactory){
        GenericRepository<Hotel> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Hotel.class);

        return repo;

    }

    @Bean
    SessionFactory provideSessionFactory(){
        return HibernateUtils.getSessionFactory();
    }
}
