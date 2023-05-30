package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.ProductionAreaDto;
import ru.erp.sfsb.dto.ProductionUnitDto;
import ru.erp.sfsb.dto.ProductionUnitPostDto;

@Component
public class CustomUnitMapper {

    public ProductionUnitDto convert(ProductionUnitPostDto postDto) {
        var productionUnitDto = new ProductionUnitDto();
        productionUnitDto.setId(postDto.getId());
        productionUnitDto.setUnitName(postDto.getUnitName());
        productionUnitDto.setPriceAmount(postDto.getPriceAmount());
        productionUnitDto.setPriceCurrency(postDto.getPriceCurrency());
        var areaDto = new ProductionAreaDto();
        areaDto.setId(postDto.getProductionAreaId());
        productionUnitDto.setProductionAreaDto(areaDto);
        return productionUnitDto;
    }
}