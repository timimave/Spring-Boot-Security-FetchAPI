package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/user")
    public String userPage() {
        return "user";
    }
    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }
}
