package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.AdditionalToolDto;
import ru.erp.sfsb.mapper.AdditionalToolMapper;
import ru.erp.sfsb.model.AdditionalTool;
import ru.erp.sfsb.repository.repos.AdditionalToolEntityRepository;
import ru.erp.sfsb.service.AdditionalToolService;

import static ru.erp.sfsb.LogTag.ADDITIONAL_TOOL_SERVICE;

@Service
@Transactional
public class AdditionalToolServiceImpl extends AbstractService<AdditionalToolDto, AdditionalTool, AdditionalToolEntityRepository, AdditionalToolMapper> implements AdditionalToolService {

    public AdditionalToolServiceImpl(AdditionalToolMapper mapper, AdditionalToolEntityRepository repository) {
        super(mapper, repository, "Additional tool", ADDITIONAL_TOOL_SERVICE);
    }
}