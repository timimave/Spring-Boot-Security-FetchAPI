package ru.kata.spring.boot_security.demo.services;

import java.util.Collection;
import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleService {
    Collection<Role> getAllRoles();
}
