package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
    @GetMapping("/getAllUsers.html")
    public String getAllUsers() {
        return "getAllUsers"; // getAllUsers - это имя  HTML файла без расширения .html
    }
    @GetMapping("/user.html")
    public String getUser() {
        return "user-info";
    }
}