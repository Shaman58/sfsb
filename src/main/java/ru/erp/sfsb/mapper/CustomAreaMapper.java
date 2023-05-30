package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.ProductionAreaDto;
import ru.erp.sfsb.dto.ProductionAreaPostDto;
import ru.erp.sfsb.dto.StoreDto;

@Component
public class CustomAreaMapper {

    public ProductionAreaDto convert(ProductionAreaPostDto postDto) {
        var productionAreaDto = new ProductionAreaDto();
        productionAreaDto.setId(postDto.getId());
        var storeDto = new StoreDto();
        storeDto.setId(postDto.getStoreDto().getId());
        productionAreaDto.setAreaName(postDto.getAreaName());
        productionAreaDto.setStoreDto(storeDto);
        return productionAreaDto;
    }
}