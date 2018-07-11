package app.model.dtos;

public class HotelDto {

    private int id;

    private String name;

    private String city;

    private int stars;

    public HotelDto() {
    }

    public HotelDto(int id, String name, String city, int stars) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.stars = stars;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStars() {
        return this.stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
