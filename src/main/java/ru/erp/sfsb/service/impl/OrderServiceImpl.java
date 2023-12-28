package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ItemDto;
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
    public List<OrderDto> getAllByQuery(String query, Pageable pageable) {
        log.info("Looking all orders with query in DB");
        var orders = repository.getOrdersByQueryString(query, pageable.getPageSize(), pageable.getPageNumber());
        var ordersDto = mapper.toDto(orders);
        ordersDto.forEach(order ->
                order.setItems(filteredItems(order.getItems(), query)));
        return ordersDto;
    }

    private List<ItemDto> filteredItems(List<ItemDto> items, String query) {
        var filtered = items.stream().filter(
                item -> (
                        item.getTechnology().getDrawingNumber().toLowerCase().contains(query.toLowerCase()) ||
                                item.getTechnology().getDrawingName().toLowerCase().contains(query.toLowerCase())
                )
        ).toList();
        if (filtered.size() == 0) {
            return items;
        }
        return filtered;
    }
}