package com.marlon.filmes_api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll() // Permite acesso a todas as rotas sem autenticação
            )
            .csrf(csrf -> csrf.disable()); // Desativa CSRF para simplificar desenvolvimento

        return http.build();
    }
}