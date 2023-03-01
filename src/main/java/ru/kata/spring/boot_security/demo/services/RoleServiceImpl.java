package ru.kata.spring.boot_security.demo.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }



    @Override
    public Role getRoleById(Long id) {
         return roleRepository.findById(id).get(); //getById(id);
    }
}
