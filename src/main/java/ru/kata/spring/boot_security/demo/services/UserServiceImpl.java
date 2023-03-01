package ru.kata.spring.boot_security.demo.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

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
    @Transactional
    public boolean addUser(User user) {
        String encodedPassword = cryptPasswordEncoder.encode(
            user.getPassword());

        userRepository.saveAndFlush(new User(user.getName(), user.getLastname(),
            user.getPersonWhoStudiesJava(), encodedPassword, user.getUsername(),
            user.getRoles()));
        return true;
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        if (user == null || user.getId() == null) {
            return false;
        } else {
            User existingUser = userRepository.findById(user.getId())
                .orElse(null);
            if (existingUser == null) {
                return false;
            } else {
                existingUser.setName(user.getName());
                existingUser.setLastname(user.getLastname());
                existingUser.setPersonWhoStudiesJava(
                    user.getPersonWhoStudiesJava());
                existingUser.setPassword(
                    cryptPasswordEncoder.encode(user.getPassword()));
                existingUser.setUsername(user.getUsername());
                existingUser.setRoles(user.getRoles());
                userRepository.save(existingUser);
                return true;
            }
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
        return userRepository.findByName(username);
    }

//     for (User user : getAllUsers()) {
//        if (user.getUsername().equals(username)) {
//            return Optional.of(user).get();
//        }
//    }
//        return Optional.<User>empty().get();
}

// String sql = "SELECT * FROM users WHERE username = ?;";
//        User user = jdbcTemplate.queryForObject(sql, new Object[]{username},
//            new BeanPropertyRowMapper<>(User.class));
//        return user;