package ru.erp.sfsb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.zalando.jackson.datatype.money.MoneyModule;

@Component
public class CustomObjectMapper {

    @Bean
    public MoneyModule objectMapper() {
        return new MoneyModule().withDefaultFormatting();
    }
}