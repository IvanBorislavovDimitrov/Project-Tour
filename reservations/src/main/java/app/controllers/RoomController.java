package app.controllers;

import app.entities.TourGuide;
import app.model.dtos.*;
import app.services.api.HotelService;
import app.services.api.RoomService;
import app.services.api.TourGuideService;
import app.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RoomController {

    private final HotelService hotelService;
    private final RoomService roomService;
    private final UserService userService;
    private final TourGuideService tourGuideService;

    @Autowired
    public RoomController(HotelService hotelService, RoomService roomService, UserService userService, TourGuideService tourGuideService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.userService = userService;
        this.tourGuideService = tourGuideService;
    }

    @GetMapping("createRoom")
    public String createRoom(Model model) {
        List<HotelDto> hotelDtos = this.hotelService.getAllHotels();

        model.addAttribute("hotels", hotelDtos);
        return "createRoom";
    }

    @GetMapping("createRoom/{hotelId}")
    public String createRoom(@PathVariable String hotelId,  Model model) {

        model.addAttribute("id", hotelId);

        return "createRoomByHotelId";
    }

    @PostMapping("createRoom/{hotelId}")
    public String createRoom(@PathVariable String hotelId, RoomDtoCreate roomDtoCreate) {
        this.hotelService.addRoom(Integer.parseInt(hotelId), roomDtoCreate);

        return "redirect:/";
    }

    @GetMapping("rooms/{id}")
    public String sendToReservationForm(@PathVariable(name = "id") String id, Model model) {
        List<TourGuideDto> tourGuides = this.tourGuideService.findAll();
        model.addAttribute("guides", tourGuides);
        model.addAttribute("id", id);


        return "reserve";
    }

    @GetMapping("rooms/{roomId}/{guideId}")
    public String confirmReservation(@PathVariable(name = "roomId") String roomId,
                                     @PathVariable(name = "guideId") String guideId,
                                     Model model) {
        RoomDto room = this.roomService.getRoomById(Integer.parseInt(roomId));
        TourGuideDto tourGuide = this.tourGuideService.findById(Integer.parseInt(guideId));

        model.addAttribute("room", room);
        model.addAttribute("guide", tourGuide);
        return "confirm";
    }

    @PostMapping("rooms/{roomId}/{guideId}")
    public String detailReservation(@PathVariable(name = "roomId") String roomId,
                                    @PathVariable(name = "guideId") String guideId,
                                    ReservationDto reservationDto,
                                    Model model) {
        RoomDto room = this.roomService.getRoomById(Integer.parseInt(roomId));
        TourGuideDto tourGuide = this.tourGuideService.findById(Integer.parseInt(guideId));
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean successful = this.userService.addReservation(room, tourGuide, principal.getUsername(), reservationDto.getDate());
        if (!successful) {
            model.addAttribute("error", true);
            return "redirect:/rooms/" + roomId + "/" + guideId;
        }
        return "redirect:/profile";
    }
}

