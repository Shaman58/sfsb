package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.AdditionalToolDto;
import ru.erp.sfsb.mapper.AdditionalToolMapper;
import ru.erp.sfsb.model.AdditionalTool;
import ru.erp.sfsb.repository.AdditionalToolRepository;
import ru.erp.sfsb.service.AdditionalToolService;
import ru.erp.sfsb.service.WorkpieceService;

@Service
@Slf4j
public class AdditionalToolServiceImpl extends AbstractService<AdditionalToolDto, AdditionalTool, AdditionalToolRepository, AdditionalToolMapper> implements AdditionalToolService {

    private final WorkpieceService workpieceService;

    public AdditionalToolServiceImpl(AdditionalToolMapper mapper, AdditionalToolRepository repository, WorkpieceService workpieceService) {
        super(mapper, repository, "Additional tool");
        this.workpieceService = workpieceService;
    }

    @Override
    @Transactional
    public AdditionalToolDto save(AdditionalToolDto additionalToolDto) {
        log.info("Saving Additional tool into DB");
        additionalToolDto.setWorkpieceDto(workpieceService.get(additionalToolDto.getWorkpieceDto().getId()));
        return mapper.toDto(repository.save(mapper.toEntity(additionalToolDto)));
    }
}