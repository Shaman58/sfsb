package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.javamoney.moneta.Money;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.ToolingDto;
import ru.erp.sfsb.model.Tooling;

import java.math.BigDecimal;

@Component
public class ToolingMapper extends AbstractMapper<Tooling, ToolingDto> {

    private final ModelMapper mapper;

    @Autowired
    public ToolingMapper(ModelMapper mapper) {
        super(Tooling.class, ToolingDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(Tooling.class, ToolingDto.class)
                .addMappings(m -> m.skip(ToolingDto::setPriceAmount))
                .addMappings(m -> m.skip(ToolingDto::setPriceCurrency))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(ToolingDto.class, Tooling.class)
                .addMappings(m -> m.skip(Tooling::setPrice))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Tooling source, ToolingDto destination) {
        destination.setPriceCurrency(source.getPrice().getCurrency().getCurrencyCode());
        destination.setPriceAmount(source.getPrice().getNumber().numberValue(BigDecimal.class));
    }

    @Override
    protected void mapSpecificFields(ToolingDto source, Tooling destination) {
        destination.setPrice(Money.of(source.getPriceAmount(), source.getPriceCurrency()));
    }
}
