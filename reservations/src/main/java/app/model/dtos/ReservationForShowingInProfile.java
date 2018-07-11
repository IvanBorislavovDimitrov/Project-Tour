package app.model.dtos;

public class ReservationForShowingInProfile {

    private String date;
    private String guideName;
    private String guideNumber;

    public ReservationForShowingInProfile() {
    }

    public ReservationForShowingInProfile(String date, String guideName, String guideNumber) {
        this.date = date;
        this.guideName = guideName;
        this.guideNumber = guideNumber;
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
