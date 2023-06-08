package ru.erp.sfsb.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.ToolingDto;
import ru.erp.sfsb.model.Tooling;

@Component
public class ToolingMapper extends AbstractMapper<Tooling, ToolingDto> {

    @Autowired
    public ToolingMapper() {
        super(Tooling.class, ToolingDto.class);
    }
}
