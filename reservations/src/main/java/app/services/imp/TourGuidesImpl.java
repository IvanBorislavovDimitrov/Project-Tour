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
import java.util.stream.Collectors;

@Service
public class TourGuidesImpl implements TourGuideService {
    private static final String INVALID_GUIDE_MESSAGE = "Invalid name";
    private final GenericRepository<TourGuide> tourGuideRepository;

    @Autowired
    public TourGuidesImpl(GenericRepository<TourGuide> tourGuideRepository) {
        this.tourGuideRepository = tourGuideRepository;
    }

    @Override
    public List<TourGuideDto> findAll() {
        return tourGuideRepository.getAll().stream()
                .map(t -> new TourGuideDto(t.getId(), t.getName(), t.getPhoneNumber()))
                .collect(Collectors.toList());
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

    @Override
    public TourGuideDto findById(int id) {
        TourGuide tourGuide =  this.tourGuideRepository.getAll().stream().filter(x -> x.getId() == id).findFirst().get();
        TourGuideDto tourGuideDto = new TourGuideDto();
        tourGuideDto.setId(tourGuide.getId());
        tourGuideDto.setName(tourGuide.getName());
        tourGuideDto.setPhoneNumber(tourGuide.getPhoneNumber());

        return tourGuideDto;
    }
}
