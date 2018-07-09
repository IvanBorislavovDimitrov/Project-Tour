package app.services.api;

import app.entities.Hotel;
import app.entities.Room;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.List;

@Service
public interface RoomService {
    List<Room> getAllRooms();

    List<Room> getRoomsByCity(String city);

    Room getRoomById(int id);


    List<Room> getAllRoomsByPage(int pageNum);

    List<Room> getRoomsByCityAndPage(String city, int page);

    void createRoom(Room room);
}
