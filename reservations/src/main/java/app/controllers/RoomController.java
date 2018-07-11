package app.controllers;

import app.entities.TourGuide;
import app.services.api.HotelService;
import app.services.api.RoomService;
import app.services.api.TourGuideService;
import app.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("rooms/{id}")
    public String sendToReservationForm(@PathVariable(name = "id") String id, Model model) {
        List<TourGuide> tourGuides = this.tourGuideService.findAll();
        model.addAttribute("tourGuides", tourGuides);
        model.addAttribute("id", id);


        return "reserve";
    }

    @PostMapping("rooms/{id}")
    public String confirmReservation(@PathVariable(name = "id") String id) {
        System.out.println();
        return "redirect:/";
    }
}

