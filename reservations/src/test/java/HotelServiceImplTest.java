import app.entities.Hotel;
import app.model.dtos.HotelDto;
import app.repostiories.base.GenericRepository;
import app.services.imp.HotelServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HotelServiceImplTest {
    @Test
    public void getAllHotels_whenHotelsAreAvailable_expectTheHotels() {
        // Arrange

        List<Hotel> hotels = Arrays.asList(
                new Hotel(),
                new Hotel()
        );

        GenericRepository<Hotel> repo = new GenericRepository<Hotel>() {
            @Override
            public List<Hotel> getAll() {
                return hotels;
            }

            @Override
            public Hotel getById(int id) {
                return null;
            }

            @Override
            public Hotel create(Hotel entity) {
                return null;
            }

            @Override
            public void setEntityClass(Class<Hotel> hotelClass) {

            }
        };

        HotelServiceImpl service = new HotelServiceImpl(repo);

        // Act

        List<HotelDto> actualHotels= service.getAllHotels();

        // Assert

        Assert.assertArrayEquals(
                actualHotels.toArray(),
                hotels.toArray()
        );
    }

    @Test
    public void getById_whenNoHotelsWithId_expectNull() {
        List<Hotel> hotels = Arrays.asList(
                new Hotel(),
                new Hotel()
        );

        GenericRepository<Hotel> repo = new GenericRepository<Hotel>() {
            @Override
            public List<Hotel> getAll() {
                return hotels;
            }

            @Override
            public Hotel getById(int id) {
                return null;
            }

            @Override
            public Hotel create(Hotel entity) {
                return null;
            }

            @Override
            public void setEntityClass(Class<Hotel> hotelClass) {

            }

        };
        HotelServiceImpl service = new HotelServiceImpl(repo);

        //Act
        Hotel hotel = service.getHotelById(-1);

        //Assert

        Assert.assertNull(hotel);


    }

    @Test(expected = Exception.class)
    public void create_whenHOtelLenLessThanRequired_expectToThrow() {


        GenericRepository<Hotel> repo = new GenericRepository<Hotel>() {
            @Override
            public List<Hotel> getAll() {
                return null;
            }

            @Override
            public Hotel getById(int id) {
                return null;
            }

            @Override
            public Hotel create(Hotel entity) {
                return null;
            }

            @Override
            public void setEntityClass(Class<Hotel> hotelClass) {

            }

        };
        HotelServiceImpl service = new HotelServiceImpl(repo);
        Hotel hotel =  new Hotel();
        hotel.setName("aa");
        HotelDto hotelDto = new HotelDto();
        hotelDto.setName(hotel.getName());
        //Act
      service.createHotel(hotelDto);

        //Assert

        Assert.assertNull(hotel);


    }

    @Test
    public void create_whenHOtelIsValid_expectToBeCreated() {


        GenericRepository<Hotel> repo = new GenericRepository<Hotel>() {
            @Override
            public List<Hotel> getAll() {
                return null;
            }

            @Override
            public Hotel getById(int id) {
                return null;
            }

            @Override
            public Hotel create(Hotel entity) {
                entity.setId(1);
                return entity;
            }

            @Override
            public void setEntityClass(Class<Hotel> hotelClass) {

            }

        };
        HotelServiceImpl service = new HotelServiceImpl(repo);
        Hotel hotel =  new Hotel();
        hotel.setName("asdf");
        HotelDto hotelDto = new HotelDto();
        hotelDto.setName(hotel.getName());
        //Act
        service.createHotel(hotelDto);

        //Assert

        Assert.assertEquals(hotel.getId(), 1);



    }
}
