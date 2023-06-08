package ru.erp.sfsb.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.MeasureToolDto;
import ru.erp.sfsb.model.MeasureTool;

@Component
public class MeasureToolMapper extends AbstractMapper<MeasureTool, MeasureToolDto> {

    @Autowired
    public MeasureToolMapper() {
        super(MeasureTool.class, MeasureToolDto.class);
    }
}