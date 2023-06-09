package ru.kata.spring.boot_security.demo.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.dto.UserDTO;

public interface UserService {
    public boolean addUser(User user);
    public boolean deleteUser(Long id);
    public boolean updateUser(User user);
    public void updateUserWithRoles(Long userId, User user, Long[] roleIds); //
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles")
    public List<User> getAllUsers();
    public User getById(Long id);
    public User getUserByName(String username);
    UserDTO getUserDTOByName(String name);

}
