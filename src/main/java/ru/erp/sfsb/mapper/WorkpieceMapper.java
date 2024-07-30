package ru.erp.sfsb.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.WorkpieceDto;
import ru.erp.sfsb.model.Workpiece;

@Component
public class WorkpieceMapper extends AbstractMapper<Workpiece, WorkpieceDto> {

    public WorkpieceMapper(ModelMapper mapper) {
        super(mapper, Workpiece.class, WorkpieceDto.class);
    }
}