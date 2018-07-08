package app.services.api;

import app.entities.Destination;
import app.entities.Hotel;

import java.util.List;

public interface HotelsService {
    List<Hotel> getAllHotels();

    List<Hotel> getHotelsByDestination(String destination);

    Hotel getHotelById(int id);


    List<Hotel> getAllHotelsByPage(int pageNum);

    List<Hotel> getHotelsByDestinationAndPage(String destination, int page);

    void createHotel(Hotel hotel);

    //CRUD methods
}
