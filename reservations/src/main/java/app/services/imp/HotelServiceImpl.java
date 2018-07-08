package app.services.imp;

import app.entities.Hotel;
import app.repostiories.base.GenericRepository;
import app.services.api.HotelsService;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HotelServiceImpl implements HotelsService {

    private static final int PAGE_SIZE = 10;
    private static final int PRODUCT_LEN_MIN = 4;
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
        List<Hotel> hotels = hotelsRepository.getAll();

        return hotels.stream()
                .filter(hotel -> hotel.getDestination()
                        .getName().equals(destination))
                .collect(Collectors.toList());

    }

    @Override
    public Hotel getHotelById(int id) {
        return hotelsRepository.getById(id);
    }

    @Override
    public List<Hotel> getAllHotelsByPage(int pageNum) {
        int fromIndex = pageNum * PAGE_SIZE;
        int toIndex = (pageNum + 1) * PAGE_SIZE;

        return getAllHotels()
                .subList(fromIndex, toIndex);
    }

    @Override
    public List<Hotel> getHotelsByDestinationAndPage(String destination, int page) {
        //validation
        int fromIndex = page * PAGE_SIZE;
        int toIndex = (page + 1) * PAGE_SIZE;

        return getHotelsByDestination(destination)
                .subList(fromIndex, toIndex);
    }

    @Override
    public void createHotel(Hotel hotel) {
        if(hotel.getName().length()<PRODUCT_LEN_MIN){
            throw  new InvalidPropertyException(Hotel.class, "name", "Invalid length");
        }
        hotelsRepository.create(hotel);
    }


}
