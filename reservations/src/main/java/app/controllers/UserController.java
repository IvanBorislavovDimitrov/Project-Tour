package app.controllers;

import app.entities.User;
import app.model.dtos.UserDto;
import app.model.dtos.UserProfileDto;
import app.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(Model model) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserProfileDto user = this.userService.findByUsername(principal.getUsername());
        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("/register")
    public String goToRegisterPage() {
        return "register";
    }

    @GetMapping("/register-error")
    public String goToRegisterErrorPage(Model model) {
        model.addAttribute("registerError", true);
        return "register";
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String registerUser(UserDto userDto) {
        try {
            this.userService.register(userDto);
        } catch (IllegalArgumentException e) {
            return "redirect:/register-error";
        }
        return "redirect:/login";
    }
}
