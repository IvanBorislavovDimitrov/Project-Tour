package app.services.imp;

import app.entities.Hotel;
import app.entities.TourGuide;
import app.model.dtos.TourGuideDto;
import app.repostiories.base.GenericRepository;
import app.services.api.TourGuideService;
import app.validation_utils.ValidationUtil;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;

@Service
public class TourGuidesImpl implements TourGuideService {
    private static final String INVALID_GUIDE_MESSAGE = "Invalid name";
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
    public void register(TourGuideDto tourGuideDto) {
        if (!ValidationUtil.isValid(tourGuideDto)) {
            throw new IllegalArgumentException(INVALID_GUIDE_MESSAGE);
        }



            TourGuide tourGuide = new TourGuide();
            tourGuide.setName(tourGuideDto.getName());
            tourGuide.setPhoneNumber(tourGuideDto.getPhoneNumber());

            tourGuideRepository.create(tourGuide);


    }

    @Override
    public TourGuide findByName(String name) {
        return tourGuideRepository.getAll()
                .stream()
                .filter(guide -> guide.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
