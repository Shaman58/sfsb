package ru.erp.sfsb.service;

import org.springframework.web.multipart.MultipartFile;
import ru.erp.sfsb.dto.OrderDto;

public interface OrderService extends Service<OrderDto> {
    OrderDto addFileToOrder(Long id, MultipartFile file);
}