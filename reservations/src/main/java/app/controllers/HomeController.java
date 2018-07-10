package app.controllers;

import app.entities.Hotel;
import app.model.dtos.HotelDto;
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

    @Autowired
    public HomeController(HotelService hotelsService) {
        this.hotelsService = hotelsService;
    }

    @GetMapping(value = "/")
    public String currentUserName(Authentication authentication, Model model) {
        List<HotelDto> hotelDtos = this.hotelsService.getAllHotels();
        model.addAttribute("hotels", hotelDtos);
        if (authentication == null) {
            model.addAttribute("user", "Guest");

            return "index";
        }

        model.addAttribute("user", authentication.getName());

        return "index";
    }

    @GetMapping("/search")
    public String showHotelsByName(@RequestParam(value = "hotelName", required = false)  String name, Model model){
        List<HotelDto> hotels = this.hotelsService.getHotelsByName(name);
        model.addAttribute("searching", true);
        model.addAttribute("hotels", hotels);

        return "search";
    }

}