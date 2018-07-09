package app.entities;

import javax.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {
    private int id;
    private String name;
    private Destination destination;

    public Hotel() {
    }

    public Hotel(int id, String name, Destination destination) {
        this.id = id;
        this.name = name;
        this.destination = destination;
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

    public Hotel(String name) {
        setName(name);
    }

    @Column(name = "name", length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }


}
