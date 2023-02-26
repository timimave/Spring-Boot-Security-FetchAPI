package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.roles where u.username = :userName")
    User findByName(@Param("userName") String userName);
}


/*Этот метод определяет запрос на получение сущности `User`
 из базы данных с помощью Spring Data JPA. Он использует оператор `JOIN FETCH`,
 чтобы включить связанные сущности `Role` в результат.

В данном случае, запрос параметризован именем пользователя,
которое передается в качестве аргумента метода `findByName`.
 Например, если мы вызовем этот метод с аргументом "john_doe",
 то будет выполнен следующий SQL-запрос:

 elect u from User u join fetch u.roles where u.username = 'john_doe'
 Результатом будет сущность User с именем пользователя "john_doe" и с
 вязанными сущностями Role.*/