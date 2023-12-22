package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.dto.UserDto;
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
        Converter<String, UserDto> uuidToUser = c -> c.getSource() == null ? null : userService.get(c.getSource());
        mapper.createTypeMap(Order.class, OrderDto.class)
                .addMappings(
                        m -> m.using(uuidToUser).map(Order::getUserUuid, OrderDto::setUser)
                );
        Converter<UserDto, String> userToUuid = c -> c.getSource().getId();
        mapper.createTypeMap(OrderDto.class, Order.class)
                .addMappings(
                        m -> m.using(userToUuid).map(OrderDto::getUser, Order::setUserUuid)
                );
    }
}