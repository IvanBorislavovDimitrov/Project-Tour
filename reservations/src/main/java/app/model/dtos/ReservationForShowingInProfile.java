package app.model.dtos;

public class ReservationForShowingInProfile {

    private String date;
    private String guideName;
    private String guideNumber;
    private String hotelInfo;

    public ReservationForShowingInProfile() {
    }

    public ReservationForShowingInProfile(String date, String guideName, String guideNumber, String hotelInfo) {
        this.date = date;
        this.guideName = guideName;
        this.guideNumber = guideNumber;
        this.hotelInfo = hotelInfo;
    }

    public String getHotelInfo() {
        return this.hotelInfo;
    }

    public void setHotelInfo(String hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGuideName() {
        return this.guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getGuideNumber() {
        return this.guideNumber;
    }

    public void setGuideNumber(String guideNumber) {
        this.guideNumber = guideNumber;
    }
}
