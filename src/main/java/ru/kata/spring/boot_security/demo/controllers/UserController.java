package ru.kata.spring.boot_security.demo.controllers;


import java.security.Principal;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String userAccount(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.getUserByName(userDetails.getUsername());

        //  User user1 = userService.getById(id);
        model.addAttribute("message", "Успешно!");
        model.addAttribute("user", user);
        return "user/user";
    }
}


//    @GetMapping("")
//    public String showUserInfo
//    (@CurrentSecurityContext(expression = "authentication.principal") User principal,
//        Model model) {
//
//        Optional<User> userOptional = Optional.ofNullable(
//            userService.getUserByName(principal.getName()));
//
//        userOptional.ifPresent(user -> model.addAttribute("user", user));
//
//        User user = userService.getUserByName(principal.getName());
//        logger.debug("Principal: {}", principal);
//        model.addAttribute("user", principal);
//        return "user/user";
//    }
//}

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

//    @GetMapping("/user")
//    public String showRoles(Model model, @RequestParam() Long id) {
//        User user = userService.getById(id);
//        System.out.println(user);
//        model.addAttribute("message", "Успешно!");
//        model.addAttribute("user", user);
//        return "user";
//    }

//    @PostMapping("/logout")
//    public String logout() {
//        // Logout code here
//        return "redirect:/login";
//    }
//}

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