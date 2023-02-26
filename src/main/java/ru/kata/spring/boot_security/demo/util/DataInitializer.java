package ru.kata.spring.boot_security.demo.util;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

//@Component
//public class DataInitializer {
//
//    private final RoleRepository roleRepository;
//
//    public DataInitializer(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    @PostConstruct
//    public void initializeData() {
//        // Создаем и сохраняем несколько ролей в базу данных
//        Role adminRole = new Role("ROLE_ADMIN");
//        Role userRole = new Role("ROLE_USER");
//        roleRepository.saveAll(Arrays.asList(adminRole, userRole));
//    }
//}

/* В этом примере мы создаем компонент DataInitializer, который помечен аннотацией @Component.
 Это означает, что он является Spring бином и будет создан автоматически
  во время запуска приложения.

Метод initializeData() помечен аннотацией @PostConstruct, что означает,
 что он будет выполнен автоматически после создания всех бинов и перед запуском приложения.

В этом методе мы создаем несколько ролей и сохраняем их в базу данных с помощью roleRepository.

Теперь при запуске приложения, если база данных пуста, метод initializeData()
будет вызван и начальные данные будут добавлены в базу.*/
