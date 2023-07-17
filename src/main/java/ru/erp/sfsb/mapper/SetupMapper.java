package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.SetupDto;
import ru.erp.sfsb.model.Setup;

@Component
public class SetupMapper extends AbstractMapper<Setup, SetupDto> {

    public SetupMapper() {
        super(Setup.class, SetupDto.class);
    }
}