package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.model.UserDTO;
import ru.kata.spring.boot_security.demo.services.UserService;
    @RestController
    public class RestUserController {
        @Autowired
        private UserService userService;
        @GetMapping(value = "/userRest", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<UserDTO> getUser(Authentication authentication) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.getUserByName(userDetails.getUsername());
            UserDTO userDTO = new UserDTO(user.getId(),user.getName(),user.getLastname(),
                user.getPersonWhoStudiesJava(), user.getUsername(),user.getRoles());
            return ResponseEntity.ok(userDTO);
        }

    }
