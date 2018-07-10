package app.services.imp;

import app.entities.Hotel;
import app.entities.Room;
import app.services.api.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoomServiceImplement implements RoomService {

    @Override
    public List<Room> getAllRoomsByHotel() {
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
    public List<Room> getRoomsByHotelAndPage(Hotel hotel, int page) {
        return null;
    }

    @Override
    public void createRoom(Room room) {

    }
}
