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
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final BCryptPasswordEncoder passwordEncoder;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, BCryptPasswordEncoder passwordEncoder) {
        this.successUserHandler = successUserHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/", "/index").permitAll()
            .antMatchers("/user").hasRole("USER")
            .antMatchers("/admin").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin().successHandler(successUserHandler)
            .permitAll()
            .and()
            .logout()
            .permitAll();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
            User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder.encode("user")).roles("USER");
    }
}





//        Эти методы позволяют авторизовать запросы,
//        разрешив доступ к путям «/» и «/index»
//        для всех пользователей, (.permitAll())
//        доступ к «/user» только для пользователей с ролью «USER»
//        и доступ к «/admin» только для пользователей с ролью «ADMIN».
//        Для всех остальных путей необходима аутентификация. - .anyRequest().authenticated()
//        Кроме того, включена форма входа с обработчиком успеха .formLogin().successHandler(successUserHandler)
//         и разрешен выход из системы.