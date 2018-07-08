package app.services.imp;

import app.entities.Destination;
import app.entities.Hotel;
import app.repostiories.base.GenericRepository;
import app.services.api.HotelsService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.crypto.Des;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelsService {

    private static final int PAGE_SIZE = 10;
    private final GenericRepository<Hotel> hotelsRepository;

    public HotelServiceImpl(GenericRepository<Hotel> hotelsRepository) {
        this.hotelsRepository = hotelsRepository;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelsRepository.getAll();
    }

    @Override
    public List<Hotel> getHotelsByDestination(String destination) {
        List<Hotel> hotels= hotelsRepository.getAll();

        return hotels.stream()
                .filter(hotel -> hotel.getDesination().equals(destination))
                .collect(Collectors.toList());

    }

    @Override
    public Hotel getHotelById(int id) {
        return hotelsRepository.getById(id);
    }

    @Override
    public List<Hotel> getAllHotelsByPage(int pageNum) {
        int fromIndex = pageNum* PAGE_SIZE;
        int toIndex = (pageNum+1)* PAGE_SIZE;

        return getAllHotels()
                .subList(fromIndex, toIndex);
    }

    @Override
    public List<Hotel> getHotelsByDestinationAndPage(String destination, int page) {
        //validation
        int fromIndex = page* PAGE_SIZE;
        int toIndex = (page+1)* PAGE_SIZE;

        return getHotelsByDestination(destination)
                .subList(fromIndex, toIndex);
    }
}
