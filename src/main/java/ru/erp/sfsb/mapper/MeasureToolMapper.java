package ru.erp.sfsb.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.MeasureToolDto;
import ru.erp.sfsb.model.MeasureTool;

@Component
public class MeasureToolMapper extends AbstractMapper<MeasureTool, MeasureToolDto> {

    public MeasureToolMapper(ModelMapper mapper) {
        super(mapper, MeasureTool.class, MeasureToolDto.class);
    }
}