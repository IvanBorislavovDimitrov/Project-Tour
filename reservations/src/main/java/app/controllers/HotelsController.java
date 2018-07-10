package app.controllers;

import app.entities.Hotel;
import app.model.dtos.HotelDto;
import app.services.api.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HotelsController {

    private final HotelService hotelsService;

    @Autowired
    public HotelsController(HotelService hotelsService) {
        this.hotelsService = hotelsService;
    }

    @GetMapping("/createHotel")
    public String createHotel() {

        return "createHotel";
    }

    @PostMapping("createHotel")
    public String createHotel(HotelDto hotelDto) {
        this.hotelsService.createHotel(hotelDto);

        return "redirect:/";
    }
}
