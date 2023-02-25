package ru.kata.spring.boot_security.demo.controllers;

import java.security.Principal;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String userAccount(Principal principal, Model model) {
        if(principal != null) {
            Optional<User> userOptional = Optional.ofNullable(
                userService.getUserByName(principal.getName()));
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (user.getRoles().stream().anyMatch(role -> role.getRole().equals("ROLE_ADMIN"))) {
                    model.addAttribute("users", userService.getAllUsers());
                    return "/admin";
                }
                else {
                    model.addAttribute("user", user);
                    return "/user";
                }
            }
        }

        return "redirect:/";
    }
}
