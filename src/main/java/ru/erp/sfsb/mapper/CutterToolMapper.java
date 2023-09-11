package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.CutterToolDto;
import ru.erp.sfsb.model.CutterTool;

@Component
public class CutterToolMapper extends AbstractMapper<CutterTool, CutterToolDto> {

    public CutterToolMapper() {
        super(CutterTool.class, CutterToolDto.class);
    }
}