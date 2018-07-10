package app.controllers;

import app.entities.Hotel;
import app.model.dtos.HotelDto;
import app.services.api.HotelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HotelsController {

    private final HotelsService hotelsService;

    @Autowired
    public HotelsController(HotelsService hotelsService){
       this.hotelsService = hotelsService;
    }

    @GetMapping("/hotels/{id}")
    public String details(@PathVariable String id, Model model){
        Hotel hotel = hotelsService.getHotelById(Integer.parseInt(id));

         model.addAttribute("hotel", hotel);

         return "hotels/details";
    }

    @GetMapping("/hotels")
    public String index(Model model) {
        List<HotelDto> hotels = hotelsService.getAllHotels();

        return "index";
    }
}
