package ru.kata.spring.boot_security.demo.configs;

import java.io.IOException;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component                                // Обработчик успешной аутентификации
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    // Spring Security использует объект Authentication, пользователя авторизованной сессии.


    @Override
    public void onAuthenticationSuccess
        (HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication) throws IOException {

        Set<String> roles = AuthorityUtils.authorityListToSet(
            authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN") && roles.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/admin");
        } else if (roles.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/user");
        } else if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin");
        } else {
            httpServletResponse.sendRedirect("/roles");
        }
    }
}

// Обработчик успешного пользователя
//  HttpServletRequest -  HTTP-запрос сервлета
// HttpServletResponse - Ответ Http-сервлета
// Authentication - Аутентификация


    /*Метод onAuthenticationSuccess является частью интерфейса AuthenticationSuccessHandler,

    который используется в Spring Security для обработки успешной аутентификации пользователя.
    Он вызывается автоматически после того, как пользователь успешно аутентифицировался.

    В этом методе мы получаем объект Authentication, который содержит информацию о пользователе,
     в том числе его роли. Затем мы используем метод
     AuthorityUtils.authorityListToSet для преобразования списка ролей пользователя в множество строк.
      Далее мы проверяем, содержит ли это множество строку "USER" или "ADMIN",
       и перенаправляем пользователя на соответствующую страницу
       в зависимости от его роли, используя метод sendRedirect
       объекта HttpServletResponse.

Таким образом, данный метод позволяет перенаправить пользователя на нужную
страницу в зависимости от его роли после успешной аутентификации.*/