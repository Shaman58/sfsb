package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ToolingDto;
import ru.erp.sfsb.mapper.ToolingMapper;
import ru.erp.sfsb.model.Tooling;
import ru.erp.sfsb.repository.repos.ToolingEntityRepository;
import ru.erp.sfsb.service.ToolingService;

import java.util.List;

import static ru.erp.sfsb.LogTag.TOOLING_SERVICE;

@Service
@Slf4j
@Transactional
public class ToolingServiceImpl extends AbstractService<ToolingDto, Tooling, ToolingEntityRepository, ToolingMapper>
        implements ToolingService {

    public ToolingServiceImpl(ToolingMapper mapper, ToolingEntityRepository repository) {
        super(mapper, repository, "Tooling", TOOLING_SERVICE);
    }

    @Override
    public List<ToolingDto> getByFilter(String filter, Pageable pageable) {
        log.info("[{}] Поиск всех сущностей типа {} в БД по фильтру {}", getLogTag(), getEntityName(), filter);
        return mapper.toDto(repository
                .getAllByToolNameContainingIgnoreCaseAndDeletedIsFalseOrDescriptionContainingIgnoreCaseAndDeletedFalseAndDeletedIsFalse(filter, filter, pageable));
    }
}