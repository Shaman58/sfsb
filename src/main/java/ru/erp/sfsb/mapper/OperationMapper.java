package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.OperationDto;
import ru.erp.sfsb.model.Operation;

@Component
public class OperationMapper extends AbstractMapper<Operation, OperationDto> {
    OperationMapper() {
        super(Operation.class, OperationDto.class);
    }
}