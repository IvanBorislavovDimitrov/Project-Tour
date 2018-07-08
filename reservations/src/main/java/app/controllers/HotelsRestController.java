package app.controllers;

import app.entities.Hotel;
import app.repostiories.HibernateRepository;
import app.repostiories.base.GenericRepository;
import app.services.api.HotelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelsRestController {
    private final HotelsService hotelsService;

    @Autowired
    public HotelsRestController(HotelsService hotelsService) {
        this.hotelsService = hotelsService;

    }

//    @RequestMapping("/hotels")
//    public List<Hotel> getHotels(){
//
//        return hotelsService.getAllHotels();
//    }

    @RequestMapping("/hotels")
    public List<Hotel> getHotelsByDestination(
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) String page) {
        if (destination == null) {
            if (page.equals("")) {
                return hotelsService.getAllHotels();
            } else {
                return hotelsService.getAllHotelsByPage(Integer.parseInt(page));
            }

        } else {
            if (page.equals("")) {
                return hotelsService.getHotelsByDestination(destination);
            } else {
                return hotelsService.getHotelsByDestinationAndPage(destination, Integer.parseInt(page));
            }
        }

    }

    @RequestMapping("/hotels/{id}")
    public Hotel getHotelDetails(@PathVariable("id") String id) {
        return hotelsService.getHotelById(Integer.parseInt(id));
    }

}
