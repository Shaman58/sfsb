package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.dto.UserDto;
import ru.erp.sfsb.model.Order;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDto> {

    private final UserConverter userConverter;

    public OrderMapper(UserConverter userConverter) {
        super(Order.class, OrderDto.class);
        this.userConverter = userConverter;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Order.class, OrderDto.class)
                .addMappings(
                        m -> m.using(userConverter.uuidToUser()).map(Order::getUserUuid, OrderDto::setUser)
                );
        Converter<UserDto, String> userToUuid = c -> c.getSource().getId();
        mapper.createTypeMap(OrderDto.class, Order.class)
                .addMappings(
                        m -> m.using(userConverter.userToUuid()).map(OrderDto::getUser, Order::setUserUuid)
                );
    }
}