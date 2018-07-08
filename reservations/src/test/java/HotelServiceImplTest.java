import app.entities.Hotel;
import app.repostiories.base.GenericRepository;
import app.services.imp.HotelServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HotelServiceImplTest {
    @Test
    public void getAllHotels_whenHotelsAreAvaible_expectTheHotels() {
        // Arrange

        List<Hotel> products = Arrays.asList(
                new Hotel(),
                new Hotel()
        );

        GenericRepository<Hotel> repo = new GenericRepository<Hotel>() {
            @Override
            public List<Hotel> getAll() {
                return products;
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

        List<Hotel> actualProducts = service.getAllHotels();

        // Assert

        Assert.assertArrayEquals(
                actualProducts.toArray(),
                products.toArray()
        );
    }
}
