package ru.erp.sfsb.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.CutterToolDto;
import ru.erp.sfsb.model.CutterTool;

@Component
public class CutterToolsMapper extends AbstractMapper<CutterTool, CutterToolDto> {

    @Autowired
    public CutterToolsMapper() {
        super(CutterTool.class, CutterToolDto.class);
    }
}