package com.dwf.bank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permitir solicitudes desde localhost:4200
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite estos métodos HTTP
                .allowedHeaders("*") // Permite todos los encabezados
                .allowCredentials(true); // Permite enviar credenciales, como cookies o cabeceras de autenticación
    }
}