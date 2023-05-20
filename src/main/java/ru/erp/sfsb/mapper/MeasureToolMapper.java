package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.javamoney.moneta.Money;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.MeasureToolDto;
import ru.erp.sfsb.model.MeasureTool;

import java.math.BigDecimal;

@Component
public class MeasureToolMapper extends AbstractMapper<MeasureTool, MeasureToolDto> {

    private final ModelMapper mapper;

    @Autowired
    public MeasureToolMapper(ModelMapper mapper) {
        super(MeasureTool.class, MeasureToolDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(MeasureTool.class, MeasureToolDto.class)
                .addMappings(m -> m.skip(MeasureToolDto::setPriceAmount))
                .addMappings(m -> m.skip(MeasureToolDto::setPriceCurrency))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(MeasureToolDto.class, MeasureTool.class)
                .addMappings(m -> m.skip(MeasureTool::setPrice))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(MeasureTool source, MeasureToolDto destination) {
        destination.setPriceCurrency(source.getPrice().getCurrency().getCurrencyCode());
        destination.setPriceAmount(source.getPrice().getNumber().numberValue(BigDecimal.class));
    }

    @Override
    protected void mapSpecificFields(MeasureToolDto source, MeasureTool destination) {
        destination.setPrice(Money.of(source.getPriceAmount(), source.getPriceCurrency()));
    }
}