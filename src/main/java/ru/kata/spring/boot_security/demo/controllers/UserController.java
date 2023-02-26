package ru.kata.spring.boot_security.demo.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/user")
//    public String userAccount(Principal principal, Model model) {
//        if (principal != null) {
//            Optional<User> userOptional = Optional.ofNullable(
//                userService.getUserByName(principal.getName()));
//            if (userOptional.isPresent()) {
//                User user = userOptional.get();
//                if (user.getRoles().stream()
//                                    .anyMatch(role -> role.getRole().equals("ROLE_ADMIN"))) {
//                    model.addAttribute("users", userService.getAllUsers());
//                    return "/admin";
//                } else {
//                    model.addAttribute("user", user);
//                    return "redirect:/user";
//                }
//            }
//        }
//
//        return "redirect:/";
//    }

    @GetMapping("/user")
    public String showUserAccountPage(Model model) {
        User user = new User();
        user.setUsername("username");
        user.setName("name");
        user.setLastname("lastname");
        user.setPersonWhoStudiesJava("Yes");

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));
        roles.add(new Role("ROLE_ADMIN"));
        user.setRoles(roles);

        model.addAttribute("user", user);

        return "user";
    }

    @PostMapping("/logout")
    public String logout() {
        // Logout code here
        return "redirect:/login";
    }




    //    @GetMapping("/user")
//    public String showUserAccount(Principal principal, Model model) {
//        if (principal != null) {
//            Optional<User> userOptional = Optional.ofNullable(
//                userService.getUserByName(principal.getName()));
//            if (userOptional.isPresent()) {
//                User user = userOptional.get();
//                if (user.getRoles().contains(new Role("ROLE_ADMIN"))) {
//                    model.addAttribute("users", userService.getAllUsers());
//                    return "admin";
//                } else if (!user.getRoles().isEmpty()) {
//                    model.addAttribute("user", user);
//                    return "redirect:/user";
//                }
//            }
//        }
//        return "redirect:/";
//    }


}





/* @GetMapping("/")
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
    }*/