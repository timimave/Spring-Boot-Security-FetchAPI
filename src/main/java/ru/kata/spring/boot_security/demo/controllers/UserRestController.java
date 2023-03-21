package ru.kata.spring.boot_security.demo.controllers;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@RestController
public class UserRestController {
    private final UserService userService;
    private final RoleService roleService;

    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/api/users")
    public @ResponseBody List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @PutMapping(value = "/api/{id}/editUser")
    public ResponseEntity<User>saveEditUser (@PathVariable Long id,
                                @ModelAttribute("user") User user,
                                @RequestParam(required = false, value = "roles") Long[] roleIds) {
        userService.updateUserWithRoles(id, user, roleIds);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

}
