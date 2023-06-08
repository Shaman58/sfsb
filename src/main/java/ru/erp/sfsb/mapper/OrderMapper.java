package ru.erp.sfsb.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.model.Order;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDto> {

    @Autowired
    public OrderMapper() {
        super(Order.class, OrderDto.class);
    }
}