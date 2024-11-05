package com.marlon.filmes_api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class NoSecurityConfig {
    // Não é necessário definir métodos adicionais - toda a segurança será desabilitada
}