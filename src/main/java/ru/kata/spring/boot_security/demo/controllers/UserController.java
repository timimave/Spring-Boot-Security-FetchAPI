package ru.kata.spring.boot_security.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping()
public class UserController {

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(
        UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/user")
    public String userAccount(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.getUserByName(userDetails.getUsername());
        model.addAttribute("user", user);
        redirectAttributes.addAttribute("from", "admin");
        return "userinfo/user";

    }



}

//@GetMapping("/admin")
//public String adminAccount(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
//    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//    User user = userService.getUserByName(userDetails.getUsername());
//    redirectAttributes.addAttribute("from", "admin");
//    return "redirect:/user";
//}
