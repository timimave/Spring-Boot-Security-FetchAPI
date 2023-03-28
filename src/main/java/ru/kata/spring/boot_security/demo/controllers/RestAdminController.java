package ru.kata.spring.boot_security.demo.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.exception_handler.NoUserWithSuchIdException;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.model.UserDTO;
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
    @GetMapping("/currentUser")
    @ResponseBody
    public UserDTO getCurrentUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.getUserDTOByName(userDetails.getUsername());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            UserDTO userDTO = new UserDTO(user.getId(),user.getUsername(),user.getName(),user.getLastname());
            return ResponseEntity.ok(userDTO);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> CreateNewUser(@ModelAttribute User user) {
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

    @PutMapping(value = "/{id}/editUser")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
        @RequestBody User user) {
        userService.updateUserWithRoles(id, user, user.getRoles()
            .stream()
            .map(Role::getId)
            .toArray(Long[]::new));
        return ResponseEntity.ok().build();
    }

//    private String getErrorsFromBindingResult(BindingResult bindingResult) {
//        return bindingResult.getFieldErrors()
//            .stream()
//            .map(DefaultMessageSourceResolvable::getDefaultMessage)
//            .collect(Collectors.joining("; "));
//    }

}

// http://localhost:8080/api/admin/45/delete
// http://localhost:8080/api/admin/29/editUser
// @RequestParam(name = "roles[]", required = false) Long[] roleIds