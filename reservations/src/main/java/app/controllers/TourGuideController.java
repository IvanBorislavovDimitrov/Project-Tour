package app.controllers;

import app.model.dtos.TourGuideDto;
import app.services.api.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TourGuideController {

    private final TourGuideService tourGuideService;

    @Autowired
    public TourGuideController(TourGuideService tourGuideService) {
        this.tourGuideService = tourGuideService;
    }

    @GetMapping("/addGuide")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createTourGuideView() {

        return "addGuide";
    }

    @PostMapping("/addGuide")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createTourGuideView(TourGuideDto tourGuideDto) {
        this.tourGuideService.register(tourGuideDto);

        return "redirect:/";
    }
}
