package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.ProductionAreaDto;
import ru.erp.sfsb.dto.ProductionAreaPostDto;
import ru.erp.sfsb.dto.ProductionUnitDto;
import ru.erp.sfsb.dto.StoreDto;

@Component
public class CustomAreaMapper {

    public ProductionAreaDto convert(ProductionAreaPostDto postDto) {
        var productionAreaDto = new ProductionAreaDto();
        var storeDto = new StoreDto();
        storeDto.setId(postDto.getStorePostDto().getId());
        productionAreaDto.setAreaName(postDto.getAreaName());
        productionAreaDto.setStoreDto(storeDto);
        productionAreaDto.setProductionUnitsDto(
                postDto.getProductionUnitsIds().stream().map(u -> setId(new ProductionUnitDto(), u)).toList());
        return productionAreaDto;
    }

    private ProductionUnitDto setId(ProductionUnitDto productionUnit, Long id) {
        productionUnit.setId(id);
        return productionUnit;
    }
}