package app.controllers;

import app.entities.Destination;
import app.services.api.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DestinationsController {
    private final DestinationService destinationService;

    @Autowired
    public DestinationsController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @RequestMapping("/destinations/{id}")
    public Destination getDestinationDetails(
            @PathVariable("id") String id){
        return destinationService.getDestinationById(Integer.parseInt(id));
    }
}
