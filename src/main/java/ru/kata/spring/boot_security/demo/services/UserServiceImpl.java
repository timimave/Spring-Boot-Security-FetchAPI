package ru.kata.spring.boot_security.demo.services;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.RoleDTO;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.model.UserDTO;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder cryptPasswordEncoder;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder cryptPasswordEncoder,
        RoleService roleService) {
        this.userRepository = userRepository;
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
        return user;
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
                System.out.println("User updated: " + existingUser);
                userRepository.save(existingUser);
                return true;
            }
        }
    }

    @Override
    public void updateUserWithRoles(Long userId, User user, Long[] roleIds) {
        user.setRoles(roleService.getRolesByIds(roleIds));
        user.setId(userId);
        if (StringUtils.hasText(user.getPassword())) {
            user.setPassword(cryptPasswordEncoder.encode(user.getPassword()));
        } else {
            User oldUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
            user.setPassword(oldUser.getPassword());
        }
        updateUser(user);
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

    @Override
    public UserDTO getUserDTOByName(String name) {
        User user = getUserByName(name);
        Set<RoleDTO> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(new RoleDTO(role.getId(), role.getRole()));
        }
        return new UserDTO(user.getId(), user.getName(), user.getLastname(),
            user.getPersonWhoStudiesJava(), user.getUsername(), roles);
    }
}
