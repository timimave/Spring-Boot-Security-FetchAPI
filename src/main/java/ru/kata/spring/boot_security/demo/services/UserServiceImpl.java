package ru.kata.spring.boot_security.demo.services;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder cryptPasswordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
        RoleRepository roleRepository,
        BCryptPasswordEncoder cryptPasswordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
        this.roleService = roleService;
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
    public Set<Role> getRolesByIds(Long[] roleIds) {
        Set<Role> roles = new HashSet<>();
        if (roleIds != null) {
            for (long i : roleIds) {
                roles.add(roleService.getRoleById(i));
            }
        }
        return roles;
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.findByName(username);
    }
}
