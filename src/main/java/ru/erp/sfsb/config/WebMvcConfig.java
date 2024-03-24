package ru.erp.sfsb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${cors.url}")
    private String url;
    @Value("${cors.ip}")
    private String ip;
    @Value("${cors.cp}")
    private String cp;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedOrigins("http://localhost:3000", url, ip, url + ":3000", cp + ":9000", "http:/localhost:9000")
                .allowedHeaders("Content-Type", "Authorization")
                .allowCredentials(true)
                .maxAge(-1);
    }
}