package app.services.imp;

import app.entities.Hotel;
import app.entities.Room;
import app.model.dtos.HotelDto;
import app.model.dtos.HotelsWithRoomsDto;
import app.model.dtos.RoomDto;
import app.repostiories.base.GenericRepository;
import app.services.api.HotelService;
import app.validation_utils.ValidationUtil;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
                .map(hotel -> new HotelDto(hotel.getName(), hotel.getCity(), hotel.getStars()))
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

    //TODO
    @Override
    public List<HotelsWithRoomsDto> getHotelsByName(String name) {
        List<HotelsWithRoomsDto> hotelsWithRoomsDtos = new ArrayList<>();
        List<Hotel> all = this.hotelsRepository.getAll();
        all.stream().filter(h -> h.getName().equalsIgnoreCase(name)).forEach(a -> {
            HotelsWithRoomsDto hotelsWithRoomsDto = new HotelsWithRoomsDto();
            hotelsWithRoomsDto.setCity(a.getCity());
            hotelsWithRoomsDto.setName(a.getName());
            hotelsWithRoomsDto.setStars(a.getStars());
            a.getRooms().stream().sorted((r1, r2) -> Double.compare(r2.getPrice(), r1.getPrice())).forEach(r -> {
                RoomDto roomDto = new RoomDto();
                roomDto.setId(r.getId());
                roomDto.setHotel(r.getHotel().getName());
                roomDto.setNumOfBeds(r.getNumOfBeds());
                roomDto.setPrice(r.getPrice());
                hotelsWithRoomsDto.getRoomDtos().add(roomDto);
            });
            hotelsWithRoomsDtos.add(hotelsWithRoomsDto);
        });

        return hotelsWithRoomsDtos;
    }
}
