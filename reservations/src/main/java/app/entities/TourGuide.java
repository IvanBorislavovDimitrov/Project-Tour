package app.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "guides")
public class TourGuide {
    private int id;
    private String name;


    public TourGuide() {
    }

    public TourGuide( String name) {
        setName(name);
       reservations = new HashSet<>();
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

    @ManyToMany(cascade = {
            CascadeType.ALL})
    @JoinTable(
            name = "guides_resevations",
            joinColumns = {@JoinColumn(name = "guide_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")}
    )
   private  Set<Reservation> reservations = new HashSet<>();

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
