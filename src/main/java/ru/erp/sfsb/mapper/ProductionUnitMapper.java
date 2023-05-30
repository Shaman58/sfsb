package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.javamoney.moneta.Money;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.ProductionAreaDto;
import ru.erp.sfsb.dto.ProductionUnitDto;
import ru.erp.sfsb.model.ProductionArea;
import ru.erp.sfsb.model.ProductionUnit;

import java.math.BigDecimal;

@Component
public class ProductionUnitMapper extends AbstractMapper<ProductionUnit, ProductionUnitDto> {

    private final ModelMapper mapper;

    @Autowired
    public ProductionUnitMapper(ModelMapper mapper) {
        super(ProductionUnit.class, ProductionUnitDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(ProductionUnit.class, ProductionUnitDto.class)
                .addMappings(m -> m.skip(ProductionUnitDto::setPriceAmount))
                .addMappings(m -> m.skip(ProductionUnitDto::setPriceCurrency))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(ProductionUnitDto.class, ProductionUnit.class)
                .addMappings(m -> m.skip(ProductionUnit::setPrice))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(ProductionUnit source, ProductionUnitDto destination) {
        destination.setPriceCurrency(source.getPrice().getCurrency().getCurrencyCode());
        destination.setPriceAmount(source.getPrice().getNumber().numberValue(BigDecimal.class));
        destination.setProductionAreaDto(mapper.map(source.getProductionArea(), ProductionAreaDto.class));
    }

    @Override
    protected void mapSpecificFields(ProductionUnitDto source, ProductionUnit destination) {
        destination.setPrice(Money.of(source.getPriceAmount(), source.getPriceCurrency()));
        destination.setProductionArea(mapper.map(source.getProductionAreaDto(), ProductionArea.class));
    }
}