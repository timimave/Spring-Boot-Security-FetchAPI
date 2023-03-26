package ru.kata.spring.boot_security.demo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.exception_handler.NoUserWithSuchIdException;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@RestController
@RequestMapping("/api/admin")
public class RestAdminController {

    private static final Logger logger = LoggerFactory.getLogger(RestAdminController.class);
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
    @PutMapping(value = "/{id}/editUser") // 29
    public ResponseEntity<User> updateUser (@PathVariable Long id,
                                            @ModelAttribute("user") User user,
                                            @RequestParam(required = false, value = "roles") Long[] roleIds) {
        userService.updateUserWithRoles(id, user, roleIds);
        return ResponseEntity.ok().build();

    }
}

// http://localhost:8080/api/admin/45/delete
// http://localhost:8080/api/admin/29/editUser
