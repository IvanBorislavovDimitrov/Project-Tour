package app.controllers;

import app.model.dtos.HotelDto;
import app.model.dtos.HotelsWithRoomsDto;
import app.model.dtos.TourGuideDto;
import app.services.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    private final HotelService hotelsService;
    private final TourGuideService tourGuideService;

    @Autowired
    public HomeController(HotelService hotelsService, TourGuideService tourGuideService) {
        this.hotelsService = hotelsService;
        this.tourGuideService = tourGuideService;
    }

    @GetMapping(value = "/")
    public String currentUserName(Authentication authentication, Model model) {
        List<HotelDto> hotelDtos = this.hotelsService.getAllHotels();
        model.addAttribute("hotels", hotelDtos);
        if (authentication == null) {
            model.addAttribute("user", "Guest");
            List<TourGuideDto> tourGuides = this.tourGuideService.findAll();
            model.addAttribute("guides", tourGuides);
            return "index";
        }
        List<TourGuideDto> tourGuides = this.tourGuideService.findAll();
        model.addAttribute("guides", tourGuides);
        model.addAttribute("user", authentication.getName());

        return "index";
    }

    @GetMapping("/search")
    public String showHotelsByName(@RequestParam(value = "hotelName", required = false)  String name, Model model){
        List<HotelsWithRoomsDto> hotels = this.hotelsService.getHotelsByName(name);
        model.addAttribute("searching", true);
        model.addAttribute("hotels", hotels);

        return "search";
    }

}