package ru.erp.sfsb.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@EnableJpaRepositories(basePackages = "ru.erp.sfsb.repository.repos")
@Component
public class JpaRepositoryConfig {
}