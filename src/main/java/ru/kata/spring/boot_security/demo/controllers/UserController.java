package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/user")
//    public String showUserAccountPage(Model model, @PathVariable("id") Long id) {
//        User user = userService.getById(id);
//        System.out.println(user);
//        model.addAttribute("user", user);
//        return "user";
//    }

    @GetMapping("/user")
    public String showRoles(Model model, Long id) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user";
    }

//    @PostMapping("/logout")
//    public String logout() {
//        // Logout code here
//        return "redirect:/login";
//    }
}


//    @GetMapping("/user")
//    public String showUserAccountPage(Principal principal, Model model) {
//        if (principal != null) {
//            Optional<User> userOptional = Optional.ofNullable(
//                userService.getUserByName(principal.getName()));
//            if (userOptional.isPresent()) {
//                User user = userOptional.get();
//                if (user.getRoles().stream()
//                    .anyMatch(role -> role.getRole().equals("ROLE_ADMIN"))) {
//                    model.addAttribute("users", userService.getAllUsers());
//                    return "/admin";
//                } else {
//                    model.addAttribute("user", user);
//                    return "redirect:/user";
//                }
//            }
//        }
//        return "redirect:/";
//    }


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