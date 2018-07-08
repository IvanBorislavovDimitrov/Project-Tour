package app.services.imp;

import app.entities.Destination;
import app.repostiories.base.GenericRepository;
import app.services.api.DestinationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {
   private final GenericRepository<Destination> destinationRepository;


    public DestinationServiceImpl(GenericRepository<Destination> destinationRepository) {
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
