package ru.erp.sfsb.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.MaterialDto;
import ru.erp.sfsb.model.Material;

@Component
public class MaterialMapper extends AbstractMapper<Material, MaterialDto> {

    public MaterialMapper(ModelMapper mapper) {
        super(mapper, Material.class, MaterialDto.class);
    }
}