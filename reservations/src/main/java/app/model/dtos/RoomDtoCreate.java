package app.model.dtos;

public class RoomDtoCreate {

    private int numberOfBeds;
    private double price;

    public RoomDtoCreate() {
    }

    public RoomDtoCreate(int numberOfBeds, double price) {
        this.numberOfBeds = numberOfBeds;
        this.price = price;
    }

    public int getNumberOfBeds() {
        return this.numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
