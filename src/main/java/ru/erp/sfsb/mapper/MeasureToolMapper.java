package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.MeasureToolDto;
import ru.erp.sfsb.model.MeasureTool;

@Component
public class MeasureToolMapper extends AbstractMapper<MeasureTool, MeasureToolDto> {

    public MeasureToolMapper() {
        super(MeasureTool.class, MeasureToolDto.class);
    }
}