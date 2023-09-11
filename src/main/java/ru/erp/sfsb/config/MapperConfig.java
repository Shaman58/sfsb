package ru.erp.sfsb.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.zalando.jackson.datatype.money.MoneyModule;
import ru.erp.sfsb.deserializer.DurationDeserializer;
import ru.erp.sfsb.deserializer.DurationSerializer;
import ru.erp.sfsb.dto.CutterToolDto;
import ru.erp.sfsb.dto.SpecialToolDto;
import ru.erp.sfsb.model.CutterTool;
import ru.erp.sfsb.model.SpecialTool;

import java.time.Duration;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;
import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Component
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);

        return modelMapper;
    }

    @Bean
    public MoneyModule objectMapper() {
        return new MoneyModule().withDefaultFormatting();
    }

    @Bean
    public SimpleModule durationModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Duration.class, new DurationSerializer());
        module.addDeserializer(Duration.class, new DurationDeserializer());
        return module;
    }
}