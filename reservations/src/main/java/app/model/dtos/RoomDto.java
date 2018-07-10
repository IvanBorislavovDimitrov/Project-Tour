package app.model.dtos;

import app.entities.Hotel;

import javax.validation.constraints.Min;

public class RoomDto {
    private int numOfBeds;
    private String hotel;
    private double price;

    public RoomDto() {
    }

    public int getNumOfBeds() {
        return numOfBeds;
    }

    public void setNumOfBeds(int numOfBeds) {
        this.numOfBeds = numOfBeds;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    @Min(10)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
