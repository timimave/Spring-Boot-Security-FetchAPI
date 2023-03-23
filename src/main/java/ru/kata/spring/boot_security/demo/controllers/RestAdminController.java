package ru.kata.spring.boot_security.demo.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}








////    @PutMapping(value = "/api/{id}/editUser")
////    public ResponseEntity<User>saveEditUser (@PathVariable Long id,
////                                @ModelAttribute("user") User user,
////                                @RequestParam(required = false, value = "roles") Long[] roleIds) {
////        userService.updateUserWithRoles(id, user, roleIds);
////
////        return new ResponseEntity<>(user, HttpStatus.OK);
////
////    }