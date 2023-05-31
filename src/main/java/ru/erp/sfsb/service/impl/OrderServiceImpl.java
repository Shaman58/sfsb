package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.mapper.OrderMapper;
import ru.erp.sfsb.model.Order;
import ru.erp.sfsb.repository.OrderRepository;
import ru.erp.sfsb.service.ItemService;
import ru.erp.sfsb.service.OrderService;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class OrderServiceImpl extends AbstractService<OrderDto, Order, OrderRepository, OrderMapper>
        implements OrderService {

    private final OrderMapper mapper;
    private final OrderRepository repository;
    private final ItemService itemService;

    public OrderServiceImpl(OrderMapper mapper, OrderRepository repository, ItemService itemService) {
        super(mapper, repository, "Order");
        this.mapper = mapper;
        this.repository = repository;
        this.itemService = itemService;
    }

    @Override
    @Transactional
    public OrderDto save(OrderDto orderDto) {
        log.info("Saving Order into DB");
        orderDto.setItemDtos(orderDto.getItemDtos().stream().map(e -> itemService.get(e.getId())).collect(toList()));
        return mapper.toDto(repository.save(mapper.toEntity(orderDto)));
    }
}