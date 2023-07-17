package ru.erp.sfsb.config;

import com.github.petrovich4j.Petrovich;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PetrovichConfig {

    @Bean
    public Petrovich petrovich() {
        return new Petrovich();
    }
}