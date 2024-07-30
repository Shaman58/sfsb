package ru.erp.sfsb.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.SetupDto;
import ru.erp.sfsb.model.Setup;

@Component
public class SetupMapper extends AbstractMapper<Setup, SetupDto> {

    public SetupMapper(ModelMapper mapper) {
        super(mapper, Setup.class, SetupDto.class);
    }
}