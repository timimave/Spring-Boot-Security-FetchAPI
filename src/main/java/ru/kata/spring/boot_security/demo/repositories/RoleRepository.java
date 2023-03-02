package ru.kata.spring.boot_security.demo.repositories;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByIdIn(Long[] ids);
}
