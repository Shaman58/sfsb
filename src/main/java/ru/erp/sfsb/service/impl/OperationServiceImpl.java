package ru.erp.sfsb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.OperationDto;
import ru.erp.sfsb.mapper.OperationMapper;
import ru.erp.sfsb.model.Operation;
import ru.erp.sfsb.repository.OperationRepository;
import ru.erp.sfsb.service.OperationService;

@Service
@Slf4j
public class OperationServiceImpl extends AbstractService<OperationDto, Operation, OperationRepository, OperationMapper>
        implements OperationService {

    public OperationServiceImpl(OperationMapper mapper, OperationRepository repository) {
        super(mapper, repository, "Operation");
    }
}