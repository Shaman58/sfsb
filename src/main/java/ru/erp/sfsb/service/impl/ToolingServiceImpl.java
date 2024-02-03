package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ToolingDto;
import ru.erp.sfsb.mapper.ToolingMapper;
import ru.erp.sfsb.model.Tooling;
import ru.erp.sfsb.repository.ToolingRepository;
import ru.erp.sfsb.service.ToolingService;

import java.util.List;

@Service
@Slf4j
@Transactional
public class ToolingServiceImpl extends AbstractService<ToolingDto, Tooling, ToolingRepository, ToolingMapper>
        implements ToolingService {

    public ToolingServiceImpl(ToolingMapper mapper, ToolingRepository repository) {
        super(mapper, repository, "Tooling");
    }

    @Override
    public List<ToolingDto> getByFilter(String filter, Pageable pageable) {
        log.info("Looking all Toolings in DB by filter {}", filter);
        return mapper.toDto(repository
                .getAllByToolNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(filter, filter, pageable));
    }
}