package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.WorkpieceDto;
import ru.erp.sfsb.model.Workpiece;

@Component
public class WorkpieceMapper extends AbstractMapper<Workpiece, WorkpieceDto> {

    public WorkpieceMapper() {
        super(Workpiece.class, WorkpieceDto.class);
    }
}