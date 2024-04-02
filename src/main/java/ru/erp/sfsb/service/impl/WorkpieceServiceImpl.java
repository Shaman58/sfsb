package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.WorkpieceDto;
import ru.erp.sfsb.mapper.WorkpieceMapper;
import ru.erp.sfsb.model.Workpiece;
import ru.erp.sfsb.repository.WorkpieceRepository;
import ru.erp.sfsb.service.WorkpieceService;

import static ru.erp.sfsb.LogTag.WORKPIECE_SERVICE;

@Service
@Transactional
public class WorkpieceServiceImpl extends AbstractService<WorkpieceDto, Workpiece, WorkpieceRepository, WorkpieceMapper> implements WorkpieceService {

    public WorkpieceServiceImpl(WorkpieceMapper mapper, WorkpieceRepository repository) {
        super(mapper, repository, "Workpiece", WORKPIECE_SERVICE);
        this.mapper = mapper;
        this.repository = repository;
    }
}