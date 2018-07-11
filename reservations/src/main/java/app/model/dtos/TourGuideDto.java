package app.model.dtos;

import javax.validation.constraints.NotNull;

public class TourGuideDto {
    private String name;
    private  String phoneNumber;

    public TourGuideDto() {
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
