package ru.kata.spring.boot_security.demo.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder cryptPasswordEncoder;

    public AdminController(UserService userService, RoleService roleService,
        BCryptPasswordEncoder cryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
    }

    @GetMapping(value = "/editUser/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        List<Role> roleList = roleService.getAllRoles();
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleService.getAllRoles());
        model.addAttribute("user", userService.getById(id));
        Set<Role> roles = user.getRoles();
        model.addAttribute("roles", roleList);
        return "/CRUD/editUser";
    }

    @GetMapping(value = "/addUser") // 2
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "/CRUD/addUser";
    }

    @GetMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PatchMapping(value = "/editUser/{getId}")
    public String saveEditUser(@PathVariable Long getId,
        @ModelAttribute("user") User user,
        @RequestParam(required = false, value = "roleIds") Long[] roleIds) {

        Set<Role> roles = new HashSet<>();
        if (roleIds != null) {
            for (long i : roleIds) {
                roles.add(roleService.getRoleById(i));
            }
        }

        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/addUser") // 3
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user); //
        return "redirect:/admin";
    }

    @GetMapping("/admin") // 1
    public String adminAcc(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/admin";
    }
}
