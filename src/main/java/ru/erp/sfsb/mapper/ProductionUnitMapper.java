package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.ProductionUnitDto;
import ru.erp.sfsb.model.ProductionUnit;

@Component
public class ProductionUnitMapper extends AbstractMapper<ProductionUnit, ProductionUnitDto> {

    public ProductionUnitMapper() {
        super(ProductionUnit.class, ProductionUnitDto.class);
    }
}