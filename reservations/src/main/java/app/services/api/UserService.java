package app.services.api;

import app.entities.User;
import app.model.dtos.*;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    void register(UserDto userDto);

    UserProfileDto findByUsername(String username);

    void addReservation(RoomDto room, TourGuideDto tourGuide, String username, String date);

    List<ReservationForShowingInProfile> getAllReservationsForShowing(String username);


}
