package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.mapper.OrderMapper;
import ru.erp.sfsb.model.Order;
import ru.erp.sfsb.repository.OrderRepository;
import ru.erp.sfsb.service.ItemService;
import ru.erp.sfsb.service.OrderService;

import java.util.List;

import static java.lang.String.format;
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
    public List<OrderDto> getAll() {
        log.info("Looking all Orders in DB");
        var orders = repository.findAll().stream().map(mapper::toDto).collect(toList());
        orders.forEach(this::deleteAreaAndSetUnits);
        return orders;
    }

    @Override
    @Transactional
    public OrderDto get(@PathVariable Long id) {
        log.info("Looking Order with id={} in DB", id);
        var order = mapper.toDto((repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("There is no Order with id=%d in database", id)))));
        deleteAreaAndSetUnits(order);
        return order;
    }

    @Override
    @Transactional
    public OrderDto save(OrderDto orderDto) {
        log.info("Saving Order into DB");
        orderDto.setItems(orderDto.getItems().stream().map(e -> itemService.get(e.getId())).collect(toList()));
        return mapper.toDto(repository.save(mapper.toEntity(orderDto)));
    }

    private void deleteAreaAndSetUnits(OrderDto orderDto) {
        orderDto.getItems().forEach(item -> item.getTechnology()
                .getSetups().forEach(setup -> setup.getProductionUnit()
                        .getProductionArea()
                        .setStore(null)));
    }
}