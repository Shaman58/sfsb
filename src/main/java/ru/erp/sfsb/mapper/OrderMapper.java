package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.model.Order;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDto> {

    public OrderMapper() {
        super(Order.class, OrderDto.class);
    }
}