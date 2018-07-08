package app.services.api;

import app.entities.Destination;

import java.util.List;

public interface DestinationService {
    List<Destination> getAllDestinations();

    Destination getDestinationById(int id);

    List<Destination> getAllDestinationsByPage(int page);

    void createDestination(Destination destination);


}
