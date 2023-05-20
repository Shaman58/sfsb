package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.MeasureToolDto;
import ru.erp.sfsb.mapper.MeasureToolMapper;
import ru.erp.sfsb.model.MeasureTool;
import ru.erp.sfsb.repository.MeasureToolRepository;
import ru.erp.sfsb.service.MeasureToolService;

@Service
@Slf4j
public class MeasureToolServiceImpl extends AbstractService<MeasureToolDto, MeasureTool, MeasureToolRepository, MeasureToolMapper> implements MeasureToolService {

    public MeasureToolServiceImpl(MeasureToolMapper mapper, MeasureToolRepository repository) {
        super(mapper, repository, "Measure tool");
    }

    @Override
    @Transactional
    public MeasureToolDto save(MeasureToolDto measureToolDto) {
        log.info("Saving Measure tool into DB");
        return mapper.toDto(repository.save(mapper.toEntity(measureToolDto)));
    }
}