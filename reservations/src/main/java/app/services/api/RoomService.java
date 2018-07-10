package app.services.api;

import app.entities.Hotel;
import app.entities.Room;
import app.model.dtos.RoomDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    List<RoomDto> getAllRoomsByHotel(String hotelName);

    Room getRoomById(int id);

    List<RoomDto> getRoomsByHotelAndPage(String hotel, int page);

    void createRoom(RoomDto room);

}
