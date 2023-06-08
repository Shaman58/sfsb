package ru.erp.sfsb.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.MaterialDto;
import ru.erp.sfsb.model.Material;

@Component
public class MaterialMapper extends AbstractMapper<Material, MaterialDto> {

    @Autowired
    public MaterialMapper() {
        super(Material.class, MaterialDto.class);
    }
}