package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.MeasureToolDto;
import ru.erp.sfsb.mapper.MeasureToolMapper;
import ru.erp.sfsb.model.MeasureTool;
import ru.erp.sfsb.repository.repos.MeasureToolEntityRepository;
import ru.erp.sfsb.service.MeasureToolService;

import java.util.List;

import static ru.erp.sfsb.LogTag.MEASURE_TOOL_SERVICE;

@Service
@Slf4j
@Transactional
public class MeasureToolServiceImpl extends AbstractService<MeasureToolDto, MeasureTool, MeasureToolEntityRepository, MeasureToolMapper> implements MeasureToolService {

    public MeasureToolServiceImpl(MeasureToolMapper mapper, MeasureToolEntityRepository repository) {
        super(mapper, repository, "Measure tool", MEASURE_TOOL_SERVICE);
    }

    @Override
    public List<MeasureToolDto> getByFilter(String filter, Pageable pageable) {
        log.info("[{}] Поиск всех сущностей типа {} в БД по фильтру {}", getLogTag(), getEntityName(), filter);
        return mapper.toDto(repository
                .getAllByToolNameContainingIgnoreCaseAndDeletedIsFalseOrDescriptionContainingIgnoreCaseAndDeletedFalseAndDeletedIsFalse(filter, filter, pageable));
    }
}