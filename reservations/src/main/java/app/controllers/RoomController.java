package app.controllers;

import app.entities.Room;
import app.model.dtos.RoomDto;
import app.services.api.RoomService;
import app.services.imp.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.Positive;
import java.util.List;

@Controller
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping("/rooms/{id}")
    public String details(@PathVariable String id, Model model){
        Room room = roomService.getRoomById(Integer.parseInt(id));

        model.addAttribute("room", room);

        return "rooms/details";
    }
    @GetMapping("/rooms")
    public String index(Model model) {
        List<RoomDto> rooms = roomService.getAllRoomsByHotel();

        return "index";
    }

    @PostMapping("/rooms")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String create(RoomDto roomdto){

        this.roomService.createRoom(roomdto);
        return "redirect:/";
    }
}
