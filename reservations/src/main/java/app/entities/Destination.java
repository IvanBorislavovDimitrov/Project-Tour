package app.entities;

import app.entities.base.ModelEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "destinations")
public class Destination implements ModelEntity {

    private int id;
    private String name;
    private Set<Hotel> hotels;

    public Destination() {
    }

    public Destination(int id, String name, Set<Hotel> hotels) {
        this.setId(id);
        this.setName(name);
        this.setHotels(hotels);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Override
    public int getId() {
        return id;
    }

    @Override
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

    @OneToMany(mappedBy = "destination", fetch = FetchType.EAGER)
    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }

}
