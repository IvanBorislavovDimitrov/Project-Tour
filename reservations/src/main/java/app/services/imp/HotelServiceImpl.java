package app.services.imp;

import app.entities.Hotel;
import app.entities.Room;
import app.model.dtos.HotelDto;
import app.repostiories.base.GenericRepository;
import app.services.api.HotelService;
import app.validation_utils.ValidationUtil;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    private static final int PAGE_SIZE = 10;
    private final GenericRepository<Hotel> hotelsRepository;
    private final GenericRepository<Room> roomGenericRepository;

    @Autowired
    public HotelServiceImpl(GenericRepository<Hotel> hotelsRepository, GenericRepository<Room> roomGenericRepository) {
        this.hotelsRepository = hotelsRepository;
        this.roomGenericRepository = roomGenericRepository;
    }

    @Override
    public List<HotelDto> getAllHotels() {
        return hotelsRepository.getAll().stream()
                .sorted((h1, h2) -> Integer.compare(h2.getStars(), h1.getStars()))
                .map(hotel -> new HotelDto() {{
                    this.setName(hotel.getName());
                    this.setCity(hotel.getCity());
                    this.setStars(hotel.getStars());
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
        if (!ValidationUtil.isValid(hotelDto)) {
            throw new IllegalArgumentException("Invalid hotel!");
        }
        Hotel hotel = new Hotel();
        hotel.setStars(hotelDto.getStars());
        hotel.setName(hotelDto.getName());
        hotel.setCity(hotelDto.getCity());

        this.hotelsRepository.create(hotel);
    }

    @Override
    public List<HotelDto> getHotelsByName(String name) {
        return this.getAllHotels().stream()
                .filter(hotelDto -> hotelDto.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
}
