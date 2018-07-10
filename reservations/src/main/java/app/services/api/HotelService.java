package app.services.api;

import app.entities.Hotel;
import app.model.dtos.HotelDto;

import java.util.List;

public interface HotelService {
    List<HotelDto> getAllHotels();

    Hotel getHotelById(int id);

    List<HotelDto> getAllHotelsByPage(int pageNum);

    void createHotel(HotelDto hotel);

    List<HotelDto> getHotelsByName(String name);
    //CRUD methods
}
