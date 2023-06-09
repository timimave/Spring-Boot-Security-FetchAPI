package ru.kata.spring.boot_security.demo.controllers;


import java.util.List;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller()
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/admin")
    public String adminAcc(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.getUserByName(userDetails.getUsername());
          model.addAttribute("user", user);
        model.addAttribute("roleList",roleService.getAllRoles());
        model.addAttribute("users", userService.getAllUsers());
        return "main/main";
    }

    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable Long id, Model model) {
        List<Role> roleList = roleService.getAllRoles();
        User user = userService.getById(id);
        Set<Role> roles = user.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleList);
        model.addAttribute("roles", roles);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/admin/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PutMapping(value = "/admin/{id}/editUser")
    public String saveEditUser (@PathVariable Long id,
                                @ModelAttribute("user") User user,
                                @RequestParam(required = false, value = "roles") Long[] roleIds) {
        userService.updateUserWithRoles(id, user, roleIds);
        return "redirect:/admin";
    }

    @PostMapping(value = "/addUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user); //
        return "redirect:/admin";
    }
}
