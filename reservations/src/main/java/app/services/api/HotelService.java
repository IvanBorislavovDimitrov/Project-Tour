package app.services.api;

import app.entities.Hotel;
import app.model.dtos.HotelDto;
import app.model.dtos.HotelsWithRoomsDto;
import app.model.dtos.RoomDtoCreate;

import java.util.List;

public interface HotelService {
    List<HotelDto> getAllHotels();

    Hotel getHotelById(int id);

    List<HotelDto> getAllHotelsByPage(int pageNum);

    void createHotel(HotelDto hotel);

    List<HotelsWithRoomsDto> getHotelsByName(String name);

    void addRoom(int i, RoomDtoCreate roomDtoCreate);
}
