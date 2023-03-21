package ru.kata.spring.boot_security.demo.controllers;

import java.util.HashSet;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

//@RestController
//@RequestMapping("/admin")
//public class AdminRestController {
//    private final UserService userService;
//    private final RoleService roleService;
//
//    public AdminRestController(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//
//    @GetMapping
//    public ResponseEntity<User> adminAcc(Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        User user = userService.getUserByName(userDetails.getUsername());
//        return ResponseEntity.ok(user);
//    }
//
//    @GetMapping("/editUser/{id}")
//    public ResponseEntity<User> editUser(@PathVariable Long id) {
//        User user = userService.getById(id);
//        return ResponseEntity.ok(user);
//    }
//
//    @GetMapping("/addUser")
//    public ResponseEntity<User> addUser() {
//        User user = new User();
//        user.setRoles(new HashSet<>());
//        return ResponseEntity.ok(user);
//    }
//
//    @DeleteMapping("/{id}/delete")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PutMapping("/{id}/editUser")
//    public ResponseEntity<Void> saveEditUser (@PathVariable Long id,
//        @RequestBody User user,
//        @RequestParam(required = false, value = "roles") Long[] roleIds) {
//        userService.updateUserWithRoles(id, user, roleIds);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PostMapping("/addUser")
//    public ResponseEntity<Void> saveUser(@RequestBody User user) {
//        userService.addUser(user); //
//        return ResponseEntity.noContent().build();
//    }
//}