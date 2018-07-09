package app.entities;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    private int id;
    private int numOfBeds;
    private Hotel hotel;
    private double price;
    private String city;

    public Room() {
    }

    public Room(int id, int numOfBeds, Hotel hotel, double price, String city) {
    setId(id);
    setNumOfBeds(numOfBeds);
    setHotel(hotel);
    setPrice(price);
    setCity(city);
    }

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "BedsNumber", length = 50, nullable = false)
    public int getNumOfBeds() {
        return numOfBeds;
    }

    public void setNumOfBeds(int numOfBeds) {
        this.numOfBeds = numOfBeds;
    }

    @Column(name = "City", length = 50, nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Column(name = "price", nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
