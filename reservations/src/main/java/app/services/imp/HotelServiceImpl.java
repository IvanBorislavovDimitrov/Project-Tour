package app.services.imp;

import app.entities.Hotel;
import app.model.dtos.HotelDto;
import app.repostiories.base.GenericRepository;
import app.services.api.HotelsService;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HotelServiceImpl implements HotelsService {

    private static final int PAGE_SIZE = 10;
    private static final int HOTEL_LEN_MIN = 4;
    private final GenericRepository<Hotel> hotelsRepository;

    public HotelServiceImpl(GenericRepository<Hotel> hotelsRepository) {
        this.hotelsRepository = hotelsRepository;
    }

    @Override
    public List<HotelDto> getAllHotels() {
        return hotelsRepository.getAll().stream()
                .map(hotel -> new HotelDto() {{
                    this.setName(hotel.getName());
                }})
                .collect(Collectors.toList());
    }

    @Override
    public Hotel getHotelById(int id) {

        return hotelsRepository.getById(id);
    }

    @Override
    public List<HotelDto> getAllHotelsByPage(int pageNum) {
        int fromIndex = pageNum * PAGE_SIZE;
        int toIndex = (pageNum + 1) * PAGE_SIZE;

        return getAllHotels().subList(fromIndex, toIndex);
    }

    @Override
    public void createHotel(HotelDto hotelDto) {
        if (hotelDto.getName().length() < HOTEL_LEN_MIN) {
            throw new InvalidPropertyException(Hotel.class, "name", "Invalid length");
        }
        Hotel hotel = new Hotel() {{
            this.setName(hotelDto.getName());
        }};

        hotelsRepository.create(hotel);
    }


}
