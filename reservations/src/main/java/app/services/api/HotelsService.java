package app.services.api;

import app.entities.Hotel;
import app.model.dtos.HotelDto;

import java.util.List;

public interface HotelsService {
    List<HotelDto> getAllHotels();

    Hotel getHotelById(int id);

    List<HotelDto> getAllHotelsByPage(int pageNum);

    void createHotel(HotelDto hotel);

    //CRUD methods
}
