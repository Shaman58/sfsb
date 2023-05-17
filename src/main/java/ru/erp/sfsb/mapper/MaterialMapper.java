package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.Money;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.MaterialDto;
import ru.erp.sfsb.model.Material;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class MaterialMapper extends AbstractMapper<Material, MaterialDto> {

    private final ModelMapper mapper;

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(Material.class, MaterialDto.class)
                .addMappings(m -> m.skip(MaterialDto::setPriceAmount))
                .addMappings(m -> m.skip(MaterialDto::setPriceCurrency))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(MaterialDto.class, Material.class)
                .addMappings(m -> m.skip(Material::setPrice))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Material source, MaterialDto destination) {
        destination.setPriceCurrency(source.getPrice().getCurrency().getCurrencyCode());
        destination.setPriceAmount(source.getPrice().getNumber().numberValue(BigDecimal.class));
    }

    @Override
    protected void mapSpecificFields(MaterialDto source, Material destination) {
        destination.setPrice(Money.of(source.getPriceAmount(), source.getPriceCurrency()));
    }
}