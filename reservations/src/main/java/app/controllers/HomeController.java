package app.controllers;

import app.model.dtos.HotelDto;
import app.services.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final HotelsService hotelsService;

    @Autowired
    public HomeController(HotelsService hotelsService) {
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
}