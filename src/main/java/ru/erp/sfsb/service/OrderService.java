package ru.erp.sfsb.service;

import ru.erp.sfsb.dto.OrderDto;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderService extends Service<OrderDto> {
    List<OrderDto> getAllByQuery(String query, Pageable pageable);
}