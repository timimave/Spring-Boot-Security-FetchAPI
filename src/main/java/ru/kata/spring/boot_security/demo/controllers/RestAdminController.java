package ru.kata.spring.boot_security.demo.controllers;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.exception_handler.NoUserWithSuchIdException;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@RestController
@RequestMapping("/api/admin")
public class RestAdminController {
    private final UserService userService;
    private final RoleService roleService;

    public RestAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{userId}/delete")
    public String deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return String.format("User with id = %d was deleted", userId);
        } catch (EmptyResultDataAccessException e) {
            String message = String.format("User with id = %d , do not exist", userId);
            throw new NoUserWithSuchIdException(message);
        }
    }
}

// http://localhost:8080/api/admin/45/delete
