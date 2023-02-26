package ru.kata.spring.boot_security.demo.services;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserService {
    public boolean addUser(User user);
    public boolean deleteUser(Long id);
    public boolean updateUser(User user);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles") // @Query("from User u left join fetch u")
    public List<User> getAllUsers();
    public User getById(Long id);
    public User getUserByName(String username);
}
