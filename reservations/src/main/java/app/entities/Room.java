package app.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {
    private int id;
    private int numOfBeds;
    private Hotel hotel;
    private double price;
    private Set<Reservation> reservations;


    public Room() {
        this.reservations = new HashSet<>();
    }

    public Room(int numOfBeds, Hotel hotel, double price) {
        setNumOfBeds(numOfBeds);
        setHotel(hotel);
        setPrice(price);
        setReservations(new HashSet<>());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rooms_reservations" ,
    joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"))
    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }


}
