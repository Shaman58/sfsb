package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.WorkpieceDto;
import ru.erp.sfsb.mapper.WorkpieceMapper;
import ru.erp.sfsb.model.Workpiece;
import ru.erp.sfsb.repository.WorkpieceRepository;
import ru.erp.sfsb.service.MaterialService;
import ru.erp.sfsb.service.WorkpieceService;

@Service
@Slf4j
public class WorkpieceServiceImpl extends AbstractService<WorkpieceDto, Workpiece, WorkpieceRepository, WorkpieceMapper> implements WorkpieceService {

    private final WorkpieceMapper mapper;
    private final WorkpieceRepository repository;
    private final MaterialService materialService;

    public WorkpieceServiceImpl(WorkpieceMapper mapper, WorkpieceRepository repository, MaterialService materialService) {
        super(mapper, repository, "Workpiece");
        this.mapper = mapper;
        this.repository = repository;
        this.materialService = materialService;
    }

    @Override
    @Transactional
    public WorkpieceDto save(WorkpieceDto workpieceDto) {
        log.info("Saving Workpiece into DB");
        workpieceDto.setMaterial(materialService.get(workpieceDto.getMaterial().getId()));
        return mapper.toDto(repository.save(mapper.toEntity(workpieceDto)));
    }
}