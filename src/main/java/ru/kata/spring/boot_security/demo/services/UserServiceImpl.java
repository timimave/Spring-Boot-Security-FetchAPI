package ru.kata.spring.boot_security.demo.services;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder cryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
        RoleRepository roleRepository,
        BCryptPasswordEncoder cryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User
            (user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean addUser(User user) {
        String encodedPassword = cryptPasswordEncoder.encode(
            user.getPassword());
        userRepository.saveAndFlush(new User(user.getName(), user.getLastname(),
            user.getPersonWhoStudiesJava(), encodedPassword, user.getUsername(),
            user.getRoles()));
        return true;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        if (user == null){
            return false;
        }
        else {
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public User getUserByName(String username) {
        for (User user : getAllUsers()) {
            if (user.getUsername().equals(username)) {
                return Optional.of(user).get();
            }
        }
        return Optional.<User>empty().get();
    }
}
