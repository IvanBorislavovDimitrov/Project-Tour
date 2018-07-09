package app.services.imp;

import app.entities.Room;
import app.services.api.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoomServiceImplement implements RoomService {
     private final RoomService roomService;

     @Autowired
    public RoomServiceImplement(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public List<Room> getAllRooms() {
        return null;
    }

    @Override
    public List<Room> getRoomsByCity(String city) {
        return null;
    }

    @Override
    public Room getRoomById(int id) {
        return null;
    }

    @Override
    public List<Room> getAllRoomsByPage(int pageNum) {
        return null;
    }

    @Override
    public List<Room> getRoomsByCityAndPage(String city, int page) {
        return null;
    }

    @Override
    public void createRoom(Room room) {

    }
}
