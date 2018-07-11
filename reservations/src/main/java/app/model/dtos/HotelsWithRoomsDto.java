package app.model.dtos;

import java.util.ArrayList;
import java.util.List;

public class HotelsWithRoomsDto {

    private String name;

    private String city;

    private int stars;

    private List<RoomDto> roomDtos;

    public HotelsWithRoomsDto() {
        this.roomDtos = new ArrayList<>();
    }

    public HotelsWithRoomsDto(String name, String city, int stars) {
        this.name = name;
        this.city = city;
        this.stars = stars;
        this.roomDtos = new ArrayList<>();
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStars() {
        return this.stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoomDto> getRoomDtos() {
        return this.roomDtos;
    }

    public void setRoomDtos(List<RoomDto> roomDtos) {
        this.roomDtos = roomDtos;
    }
}
