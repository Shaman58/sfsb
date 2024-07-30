package ru.erp.sfsb.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.AdditionalToolDto;
import ru.erp.sfsb.model.AdditionalTool;

@Component
public class AdditionalToolMapper extends AbstractMapper<AdditionalTool, AdditionalToolDto> {

    public AdditionalToolMapper(ModelMapper mapper) {
        super(mapper, AdditionalTool.class, AdditionalToolDto.class);
    }
}