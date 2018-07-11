package app.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "guides")
public class TourGuide {

    private int id;
    private String name;
    private  String phoneNumber;

    private Set<Reservation> reservations;

    public TourGuide() {
        this.reservations = new HashSet<>();
    }

    public TourGuide(String name) {
        setName(name);
        this.reservations = new HashSet<>();
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

    @Column(name = "name", length = 55, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "phone", length = 55, nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    @OneToMany(mappedBy = "tourGuide")
    public Set<Reservation> getReservations() {
        return this.reservations;
    }
}
