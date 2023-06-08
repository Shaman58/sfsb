package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.ProductionAreaDto;
import ru.erp.sfsb.model.ProductionArea;

@Component
public class ProductionAreaMapper extends AbstractMapper<ProductionArea, ProductionAreaDto> {

    public ProductionAreaMapper() {
        super(ProductionArea.class, ProductionAreaDto.class);
    }
}