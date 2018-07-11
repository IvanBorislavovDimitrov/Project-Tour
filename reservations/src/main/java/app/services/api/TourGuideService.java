package app.services.api;

import app.entities.TourGuide;
import app.model.dtos.TourGuideDto;

import java.util.List;

public interface TourGuideService {
    List<TourGuideDto> findAll();

    void register(TourGuideDto tourGuidedto);

    TourGuide findByName(String Name);

    TourGuideDto findById(int id);
}
