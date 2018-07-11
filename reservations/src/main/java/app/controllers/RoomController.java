package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoomController {

    @GetMapping("rooms/{id}")
    public String sendToReservationForm(@PathVariable(name = "id") String id, Model model) {
        model.addAttribute("id", id);
        return "reserve";
    }

    @PostMapping("rooms/{id}")
    public String confirmReservation(@PathVariable(name = "id") String id) {
        System.out.println();
        return "redirect:/";
    }
}

