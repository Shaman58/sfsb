package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.SpecialToolDto;
import ru.erp.sfsb.mapper.SpecialToolMapper;
import ru.erp.sfsb.model.SpecialTool;
import ru.erp.sfsb.repository.SpecialToolRepository;
import ru.erp.sfsb.service.SpecialToolService;
import ru.erp.sfsb.service.WorkpieceService;

@Service
@Slf4j
public class SpecialToolServiceImpl extends AbstractService<SpecialToolDto, SpecialTool, SpecialToolRepository, SpecialToolMapper>
        implements SpecialToolService {

    private final WorkpieceService workpieceService;

    public SpecialToolServiceImpl(SpecialToolMapper mapper, SpecialToolRepository repository, WorkpieceService workpieceService) {
        super(mapper, repository, "Special tool");
        this.workpieceService = workpieceService;
    }

    @Override
    @Transactional
    public SpecialToolDto save(SpecialToolDto specialToolDto) {
        log.info("Saving Special tool into DB");
        specialToolDto.setWorkpieceDto(workpieceService.get(specialToolDto.getWorkpieceDto().getId()));
        return mapper.toDto(repository.save(mapper.toEntity(specialToolDto)));
    }
}