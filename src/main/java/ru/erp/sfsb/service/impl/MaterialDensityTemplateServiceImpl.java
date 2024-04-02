package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.MaterialDensityTemplateDto;
import ru.erp.sfsb.mapper.MaterialDensityTemplateMapper;
import ru.erp.sfsb.model.MaterialDensityTemplate;
import ru.erp.sfsb.repository.MaterialDensityTemplateRepository;
import ru.erp.sfsb.service.MaterialDensityTemplateService;

import static ru.erp.sfsb.LogTag.MATERIAL_DENSITY_SERVICE;

@Service
@Transactional
public class MaterialDensityTemplateServiceImpl extends AbstractService<MaterialDensityTemplateDto, MaterialDensityTemplate, MaterialDensityTemplateRepository, MaterialDensityTemplateMapper>
        implements MaterialDensityTemplateService {

    public MaterialDensityTemplateServiceImpl(MaterialDensityTemplateMapper mapper, MaterialDensityTemplateRepository repository) {
        super(mapper, repository, "MaterialDensityTemplate", MATERIAL_DENSITY_SERVICE);
    }
}