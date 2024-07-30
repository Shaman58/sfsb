package ru.erp.sfsb.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.OperationDto;
import ru.erp.sfsb.model.Operation;

@Component
public class OperationMapper extends AbstractMapper<Operation, OperationDto> {
    OperationMapper(ModelMapper mapper) {
        super(mapper, Operation.class, OperationDto.class);
    }
}