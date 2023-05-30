package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.ProductionAreaDto;
import ru.erp.sfsb.dto.StoreDto;
import ru.erp.sfsb.model.ProductionArea;
import ru.erp.sfsb.model.Store;

@Component
public class ProductionAreaMapper extends AbstractMapper<ProductionArea, ProductionAreaDto> {

    private final ModelMapper mapper;

    public ProductionAreaMapper(ModelMapper mapper) {
        super(ProductionArea.class, ProductionAreaDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(ProductionArea.class, ProductionAreaDto.class)
                .addMappings(m -> m.skip(ProductionAreaDto::setProductionUnitDtoList))
                .addMappings(m -> m.skip(ProductionAreaDto::setStoreDto))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(ProductionAreaDto.class, ProductionArea.class)
                .addMappings(m -> m.skip(ProductionArea::setProductionUnits))
                .addMappings(m -> m.skip(ProductionArea::setStore))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(ProductionArea source, ProductionAreaDto destination) {
        destination.setStoreDto(mapper.map(source.getStore(), StoreDto.class));
    }

    @Override
    protected void mapSpecificFields(ProductionAreaDto source, ProductionArea destination) {
        destination.setStore(mapper.map(source.getStoreDto(), Store.class));
    }
}