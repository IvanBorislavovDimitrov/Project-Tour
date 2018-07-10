package app.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reservations")
public class Reservation {
    private int id;
     private Set<Room> rooms;

    public Reservation() {
        rooms = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {

    }

    @ManyToMany(mappedBy = "reservations", fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Room.class)
    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
