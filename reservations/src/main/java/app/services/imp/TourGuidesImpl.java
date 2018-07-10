package app.services.imp;

import app.entities.TourGuide;
import app.repostiories.base.GenericRepository;
import app.services.api.TourGuideService;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;

@Service
public class TourGuidesImpl implements TourGuideService {
    private static final int GUIDE_LEN_MIN = 5;
    private final GenericRepository<TourGuide> tourGuideRepository;

    @Autowired
    public TourGuidesImpl(GenericRepository<TourGuide> tourGuideRepository) {
        this.tourGuideRepository = tourGuideRepository;
    }

    @Override
    public List<TourGuide> findAll() {
        return tourGuideRepository.getAll();
    }

    @Override
    public void register(TourGuide tourGuide) {
        if(tourGuide.getName().length()<GUIDE_LEN_MIN){
            throw new InvalidPropertyException(TourGuide.class, "name", "Invalid length");
        }

        tourGuideRepository.create(tourGuide);
    }

    @Override
    public TourGuide findByName(String Name) {
        return null;
    }
}
