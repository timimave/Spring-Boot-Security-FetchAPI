package ru.kata.spring.boot_security.demo.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

public interface RoleService {
    List<Role> getAllRoles();
    public Set<Role> getRolesByIds(Long[] roleIds);

}
