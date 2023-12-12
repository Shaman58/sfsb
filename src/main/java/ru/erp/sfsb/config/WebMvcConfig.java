package ru.erp.sfsb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${cors.url}")
    private String url;
    @Value("${cors.ip}")
    private String ip;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedOrigins("http://localhost:3000", url, ip)
                .allowedHeaders("Content-Type", "Authorization")
                .allowCredentials(true)
                .maxAge(-1);
    }
}