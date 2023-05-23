package ru.erp.sfsb.service.impl;

import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ToolingDto;
import ru.erp.sfsb.mapper.ToolingMapper;
import ru.erp.sfsb.model.Tooling;
import ru.erp.sfsb.repository.ToolingRepository;
import ru.erp.sfsb.service.ToolingService;

@Service
public class ToolingServiceImpl extends AbstractService<ToolingDto, Tooling, ToolingRepository, ToolingMapper>
        implements ToolingService {

    public ToolingServiceImpl(ToolingMapper mapper, ToolingRepository repository) {
        super(mapper, repository, "Tooling");
    }
}