package app.entities;

import app.entities.base.Entity;

import java.util.Set;

public class Destination implements Entity {
    private int id;
    private String name;
    private Set<Hotel> hotels;

    public Destination(int id, String name, Set<Hotel> hotels) {
        setId(id);
        setName(name);
        setHotels(hotels);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
