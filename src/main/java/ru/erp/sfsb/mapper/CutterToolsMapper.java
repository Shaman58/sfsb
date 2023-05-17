package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.javamoney.moneta.Money;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.CutterToolDto;
import ru.erp.sfsb.model.CutterTool;

import java.math.BigDecimal;

@Component
public class CutterToolsMapper extends AbstractMapper<CutterTool, CutterToolDto> {

    private final ModelMapper mapper;

    @Autowired
    public CutterToolsMapper(ModelMapper mapper) {
        super(CutterTool.class, CutterToolDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(CutterTool.class, CutterToolDto.class)
                .addMappings(m -> m.skip(CutterToolDto::setPriceAmount))
                .addMappings(m -> m.skip(CutterToolDto::setPriceCurrency))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(CutterToolDto.class, CutterTool.class)
                .addMappings(m -> m.skip(CutterTool::setPrice))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(CutterTool source, CutterToolDto destination) {
        destination.setPriceCurrency(source.getPrice().getCurrency().getCurrencyCode());
        destination.setPriceAmount(source.getPrice().getNumber().numberValue(BigDecimal.class));
    }

    @Override
    protected void mapSpecificFields(CutterToolDto source, CutterTool destination) {
        destination.setPrice(Money.of(source.getPriceAmount(), source.getPriceCurrency()));
    }
}
