package app.services.imp;

import app.entities.Destination;
import app.repostiories.base.GenericRepository;
import app.services.api.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DestinationServiceImpl implements DestinationService {

    private final GenericRepository<Destination> destinationRepository;

    @Autowired
    public DestinationServiceImpl(@Qualifier("Destination") GenericRepository<Destination> destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public List<Destination> getAllDestinations() {
        return destinationRepository.getAll();
    }

    @Override
    public Destination getDestinationById(int id) {
        return destinationRepository.getById(id);
    }

    @Override
    public List<Destination> getAllDestinationsByPage(int page) {
        //TODO
        return null;
    }

    @Override
    public void createDestination(Destination destination) {
        //validation
        destinationRepository.create(destination);
    }
}
