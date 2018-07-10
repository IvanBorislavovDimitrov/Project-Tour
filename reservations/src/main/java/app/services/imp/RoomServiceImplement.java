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
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomServiceImplement implements RoomService {
    private static final String INVALID_ROOM_MESSAGE = "Min price is hihger!";
    private static final int PAGE_SIZE = 10;
    private final GenericRepository<Room> roomsRepository;
    private final GenericRepository<Hotel> hotelsRepository;

    public RoomServiceImplement(GenericRepository<Room> roomsRepository, GenericRepository<Hotel> hotelsRepository) {
        this.roomsRepository = roomsRepository;
        this.hotelsRepository = hotelsRepository;
    }

    @Override
    public List<RoomDto> getAllRoomsByHotel(String hotelName) {
        List<Room> rooms = roomsRepository.getAll();


       List<RoomDto>  roomDtoToReturn = rooms.stream()
                .filter(x -> x.getHotel().getName().equals(hotelName))
                .map(r -> {
                    final RoomDto roomDto = new RoomDto();
                   roomDto.setHotel(r.getHotel().getName());
                   roomDto.setNumOfBeds(r.getNumOfBeds());
                   roomDto.setPrice(r.getPrice());

                return roomDto;
                })
                .collect(Collectors.toList());
        return roomDtoToReturn;
    }

    @Override
    public Room getRoomById(int id) {
        return roomsRepository.getById(id);
    }

    @Override
    public List<RoomDto> getRoomsByHotelAndPage(String hotelName, int pageNum) {
        int fromIndex = pageNum * PAGE_SIZE;
        int toIndex = (pageNum + 1) * PAGE_SIZE;

        return getAllRoomsByHotel(hotelName).subList(fromIndex, toIndex);
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

            Room room = new Room();
            room.setNumOfBeds(roomdto.getNumOfBeds());
            room.setPrice(roomdto.getPrice());
            room.setHotel(hotel);

            hotel.getRooms().add(room);
            roomsRepository.create(room);
        }

    }
}

