package app.services.api;

import app.entities.TourGuide;
import app.model.dtos.TourGuideDto;

import java.util.List;

public interface TourGuideService {
    List<TourGuide> findAll();

    void register(TourGuideDto tourGuidedto);

    TourGuide findByName(String Name);
}
