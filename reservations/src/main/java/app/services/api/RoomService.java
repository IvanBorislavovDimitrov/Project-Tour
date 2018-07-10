package app.services.api;

import app.entities.Hotel;
import app.entities.Room;
import app.model.dtos.RoomDto;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.List;

@Service
public interface RoomService {
    List<RoomDto> getAllRoomsByHotel();

    Room getRoomById(int id);


    List<Room> getAllRoomsByPage(int pageNum);

    List<Room> getRoomsByHotelAndPage(Hotel hotel, int page);

    void createRoom(RoomDto room);
}
