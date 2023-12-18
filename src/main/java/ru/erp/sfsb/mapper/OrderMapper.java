package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.model.Order;
import ru.erp.sfsb.service.UserService;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDto> {

    private final UserService userService;

    public OrderMapper(UserService userService) {
        super(Order.class, OrderDto.class);
        this.userService = userService;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Order.class, OrderDto.class)
                .addMappings(m -> m.skip(OrderDto::setUser)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(OrderDto.class, Order.class)
                .addMappings(m -> m.skip(Order::setUserUuid)).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(Order source, OrderDto destination) {
        destination.setUser(userService.get(source.getUserUuid()));
    }

    @Override
    void mapSpecificFields(OrderDto source, Order destination) {
        destination.setUserUuid(source.getUser().getId());
    }
}