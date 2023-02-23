package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
    @Bean
    @Primary
    public BCryptPasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}