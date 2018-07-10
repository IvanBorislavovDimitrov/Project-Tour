package app.services.imp;

import app.entities.Hotel;
import app.entities.Room;
import app.model.dtos.RoomDto;
import app.repostiories.base.GenericRepository;
import app.services.api.RoomService;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoomServiceImplement implements RoomService {
    private static final double PRICE_MIN = 10;
    private final GenericRepository<Room> roomsRepository;

    public RoomServiceImplement(GenericRepository<Room> roomsRepository) {
        this.roomsRepository= roomsRepository;
    }

    @Override
    public List<RoomDto> getAllRoomsByHotel() {
        return null;
    }

    @Override
    public Room getRoomById(int id){
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
        if (roomdto.getPrice() < PRICE_MIN) {
            throw new InvalidPropertyException(Room.class, "price", "Some price needed");
        }
       Room room = new Room(){{
            setNumOfBeds(roomdto.getNumOfBeds());
            setCity(roomdto.getCity());
           setPrice(roomdto.getPrice());

        }};

       roomsRepository.create(room);
    }
}

