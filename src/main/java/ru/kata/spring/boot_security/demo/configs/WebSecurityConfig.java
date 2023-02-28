package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;


    public WebSecurityConfig(SuccessUserHandler successUserHandler,
        BCryptPasswordEncoder passwordEncoder, UserServiceImpl userService) {
        this.successUserHandler = successUserHandler;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void config(AuthenticationManagerBuilder auth)
        throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(new BCryptPasswordEncoder());
//        auth.userDetailsService(userService)
//            .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/roles").hasRole("USER")
            .antMatchers("/admin").hasRole("ADMIN")
            .antMatchers(  "/user").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .successHandler(new SuccessUserHandler())
            .permitAll()
            .and()
            .logout()
            .permitAll();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails tool =
//            User.builder()
//                .username("tool")
//                .password(passwordEncoder().encode("tool"))
//                .roles("USER")
//                .build();
//        UserDetails admin =
//            User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(tool, admin);
//
//    }








//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//        throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("tool")
//            .password(passwordEncoder
//                .encode("tool"))
//            .roles("USER")
//            .and()
//            .withUser("admin")
//            .password(passwordEncoder.encode("admin"))
//            .roles("ADMIN");
//    }
}


//            .antMatchers("/", "/index").permitAll()
//            .antMatchers("/user").hasRole("USER")
//            .antMatchers("/admin").hasRole("ADMIN")

//            .antMatchers(  "/user").hasAnyRole("USER", "ADMIN")
//            .antMatchers("/admin", "CRUD/editUser", "CRUD/addUser").hasRole("ADMIN")
//    .formLogin().successHandler(successUserHandler)

//        Эти методы позволяют авторизовать запросы,
//        разрешив доступ к путям «/» и «/index»
//        для всех пользователей, (.permitAll())
//        доступ к «/user» только для пользователей с ролью «USER»
//        и доступ к «/admin» только для пользователей с ролью «ADMIN».
//        Для всех остальных путей необходима аутентификация. - .anyRequest().authenticated()
//        Кроме того, включена форма входа с обработчиком успеха .formLogin().successHandler(successUserHandler)
//         и разрешен выход из системы.