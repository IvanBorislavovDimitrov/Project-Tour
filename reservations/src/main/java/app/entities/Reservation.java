package app.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reservations")
public class Reservation {
    private int id;
    private Set<Room> rooms;
    private Date date;
    private User user;
    private TourGuide tourGuide;

    public Reservation() {
        rooms = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToMany(mappedBy = "reservations", fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Room.class)
    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @ManyToOne
    @JoinColumn(name = "tour_giude_id", referencedColumnName = "id")
    public TourGuide getTourGuide() {
        return this.tourGuide;
    }

    public void setTourGuide(TourGuide tourGuide) {
        this.tourGuide = tourGuide;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
