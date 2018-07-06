package app.controllers;

import app.model.dtos.UserDto;
import app.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<UserDto> userDtos = this.userService.findAll();
        model.addAttribute("users", userDtos);

        return "users";
    }

    @GetMapping("/register")
    public String goToRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String registerUser(UserDto userDto) {
        try {
            this.userService.register(userDto);
        } catch (IllegalArgumentException e) {
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}
