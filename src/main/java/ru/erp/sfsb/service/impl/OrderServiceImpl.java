package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.mapper.OrderMapper;
import ru.erp.sfsb.model.Order;
import ru.erp.sfsb.repository.OrderRepository;
import ru.erp.sfsb.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl extends AbstractService<OrderDto, Order, OrderRepository, OrderMapper>
        implements OrderService {

    public OrderServiceImpl(OrderMapper mapper, OrderRepository repository) {
        super(mapper, repository, "Order");
    }
}