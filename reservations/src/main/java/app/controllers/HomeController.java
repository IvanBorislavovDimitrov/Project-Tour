package app.controllers;

import app.entities.Hotel;
import app.services.api.HotelsService;
import app.services.imp.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    private HotelsService hotelsService;

    @Autowired
    public HomeController(HotelsService hotelsService) {
        this.hotelsService = hotelsService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
//
//    @GetMapping("/")
//    public String index(Model model) {
//        List<Hotel> hotels = hotelsService.getAllHotels();
//        model.addAttribute("message", "Hello from the other side!");
//        model.addAttribute("hotels", hotels);
//
//        return "index";
//    }
}

