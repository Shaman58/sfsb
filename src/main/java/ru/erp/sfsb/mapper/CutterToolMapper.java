package ru.erp.sfsb.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.CutterToolDto;
import ru.erp.sfsb.model.CutterTool;

@Component
public class CutterToolMapper extends AbstractMapper<CutterTool, CutterToolDto> {

    public CutterToolMapper(ModelMapper mapper) {
        super(mapper, CutterTool.class, CutterToolDto.class);
    }
}