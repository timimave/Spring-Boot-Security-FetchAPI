package ru.kata.spring.boot_security.demo.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleService {
    List<Role> getAllRoles();
     Role getRoleById(Long id);

}
