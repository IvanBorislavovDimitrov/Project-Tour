package app.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String currentUserName(Authentication authentication, Model model) {
        if (authentication == null) {
            model.addAttribute("user", "Guest");

            return "index";
        }
        model.addAttribute("user", authentication.getName());

        return "index";
    }
}