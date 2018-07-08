package app.entities;


import app.entities.base.Entity;

public class Hotel implements Entity {
    private int id;
    private String name;
    private String destination;

    public Hotel(String name) {
        setName(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesination() {
        return destination;
    }

    public void setDestination(String destination ) {
        this.destination = destination;
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
