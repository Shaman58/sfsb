package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.model.Item;
import ru.erp.sfsb.model.Technology;

@Component
public class ItemMapper extends AbstractMapper<Item, ItemDto> {

    private final ModelMapper mapper;

    @Autowired
    public ItemMapper(ModelMapper mapper) {
        super(Item.class, ItemDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(Item.class, ItemDto.class)
                .addMappings(m -> m.skip(ItemDto::setTechnologyDto))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(ItemDto.class, Item.class)
                .addMappings(m -> m.skip(Item::setTechnology))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Item source, ItemDto destination) {
        destination.setTechnologyDto(mapper.map(source.getTechnology(), TechnologyDto.class));
    }

    @Override
    protected void mapSpecificFields(ItemDto source, Item destination) {
        destination.setTechnology(mapper.map(source.getTechnologyDto(), Technology.class));
    }
}