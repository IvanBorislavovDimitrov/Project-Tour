package app.services.imp;

import app.entities.Hotel;
import app.entities.Room;
import app.model.dtos.RoomDto;
import app.repostiories.base.GenericRepository;
import app.services.api.RoomService;
import app.validation_utils.ValidationUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomServiceImplement implements RoomService {
    private static final String INVALID_ROOM_MESSAGE = "Min price is hihger!";
    private final GenericRepository<Room> roomsRepository;
    private final GenericRepository<Hotel> hotelsRepository;

    public RoomServiceImplement(GenericRepository<Room> roomsRepository, GenericRepository<Hotel> hotelsRepository) {
        this.roomsRepository = roomsRepository;
        this.hotelsRepository = hotelsRepository;
    }

    @Override
    public List<RoomDto> getAllRoomsByHotel() {
        return null;
    }

    @Override
    public Room getRoomById(int id) {
        return roomsRepository.getById(id);
    }

    @Override
    public List<Room> getAllRoomsByPage(int pageNum) {
        return null;
    }

    @Override
    public List<Room> getRoomsByHotelAndPage(Hotel hotel, int page) {
        return null;
    }

    @Override
    public void createRoom(RoomDto roomdto) {
        if (!ValidationUtil.isValid(roomdto)) {
            throw new IllegalArgumentException(INVALID_ROOM_MESSAGE);
        }


        Hotel hotel = hotelsRepository.getAll().stream()
                .filter(x -> x.getName().equals(roomdto.getHotel()))
                .findAny()
                .orElse(null);

        if (hotel != null) {

            Room room = new Room() {{
                setNumOfBeds(roomdto.getNumOfBeds());
                setCity(roomdto.getCity());
                setPrice(roomdto.getPrice());
                setHotel(hotel);
            }};

            hotel.getRooms().add(room);
            roomsRepository.create(room);
        }

    }
}

