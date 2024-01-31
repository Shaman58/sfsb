package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.MeasureToolDto;
import ru.erp.sfsb.mapper.MeasureToolMapper;
import ru.erp.sfsb.model.MeasureTool;
import ru.erp.sfsb.repository.MeasureToolRepository;
import ru.erp.sfsb.service.MeasureToolService;

import java.util.List;

@Service
@Slf4j
@Transactional
public class MeasureToolServiceImpl extends AbstractService<MeasureToolDto, MeasureTool, MeasureToolRepository, MeasureToolMapper> implements MeasureToolService {

    public MeasureToolServiceImpl(MeasureToolMapper mapper, MeasureToolRepository repository) {
        super(mapper, repository, "Measure tool");
    }

    @Override
    public List<MeasureToolDto> getByFilter(String filter, Pageable pageable) {
        log.info("Looking all Measure tools in DB by filter {}", filter);
        return mapper.toDto(repository
                .getAllByToolNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(filter, filter, pageable));
    }
}