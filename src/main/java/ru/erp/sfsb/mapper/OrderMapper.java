package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.model.Item;
import ru.erp.sfsb.model.Order;

import static java.util.stream.Collectors.toList;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDto> {

    private final ModelMapper mapper;

    @Autowired
    public OrderMapper(ModelMapper mapper) {
        super(Order.class, OrderDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(Order.class, OrderDto.class)
                .addMappings(m -> m.skip(OrderDto::setItemDtos))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(OrderDto.class, Order.class)
                .addMappings(m -> m.skip(Order::setItems))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Order source, OrderDto destination) {
        destination.setItemDtos(source.getItems().stream().map(e -> mapper.map(e, ItemDto.class)).collect(toList()));
    }

    @Override
    protected void mapSpecificFields(OrderDto source, Order destination) {
        destination.setItems(source.getItemDtos().stream().map(e -> mapper.map(e, Item.class)).collect(toList()));
    }
}