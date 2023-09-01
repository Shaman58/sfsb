package ru.erp.sfsb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.MaterialDensityTemplateDto;
import ru.erp.sfsb.mapper.MaterialDensityTemplateMapper;
import ru.erp.sfsb.model.MaterialDensityTemplate;
import ru.erp.sfsb.repository.MaterialDensityTemplateRepository;
import ru.erp.sfsb.service.MaterialDensityTemplateService;

@Service
@Slf4j
public class MaterialDensityTemplateServiceImpl extends AbstractService<MaterialDensityTemplateDto, MaterialDensityTemplate, MaterialDensityTemplateRepository, MaterialDensityTemplateMapper>
        implements MaterialDensityTemplateService {

    public MaterialDensityTemplateServiceImpl(MaterialDensityTemplateMapper mapper, MaterialDensityTemplateRepository repository) {
        super(mapper, repository, "MaterialDensityTemplate");
    }
}