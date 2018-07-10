package app.services.api;

import app.entities.TourGuide;

import java.util.List;

public interface TourGuideService {
    List<TourGuide> findAll();

    void register(TourGuide tourGuide);

    TourGuide findByName(String Name);
}
