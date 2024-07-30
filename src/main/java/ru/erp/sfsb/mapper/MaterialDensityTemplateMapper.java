package ru.erp.sfsb.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.MaterialDensityTemplateDto;
import ru.erp.sfsb.model.MaterialDensityTemplate;

@Component
public class MaterialDensityTemplateMapper extends AbstractMapper<MaterialDensityTemplate, MaterialDensityTemplateDto> {
    MaterialDensityTemplateMapper(ModelMapper mapper) {
        super(mapper, MaterialDensityTemplate.class, MaterialDensityTemplateDto.class);
    }
}