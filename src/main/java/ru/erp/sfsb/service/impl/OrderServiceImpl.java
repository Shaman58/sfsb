package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.mapper.OrderMapper;
import ru.erp.sfsb.model.Order;
import ru.erp.sfsb.repository.OrderRepository;
import ru.erp.sfsb.service.OrderService;

import java.util.List;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl extends AbstractService<OrderDto, Order, OrderRepository, OrderMapper>
        implements OrderService {

    public OrderServiceImpl(OrderMapper mapper, OrderRepository repository) {
        super(mapper, repository, "Order");
    }

    @Override
    public List<OrderDto> getAllByQuery(String query) {
        log.info("Looking all orders with query in DB");
        var entities = repository.getOrdersByQueryString(query);
        return mapper.toDto(entities);
    }
}