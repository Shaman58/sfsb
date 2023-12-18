package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.mapper.OrderMapper;
import ru.erp.sfsb.model.Order;
import ru.erp.sfsb.repository.OrderRepository;
import ru.erp.sfsb.service.FileService;
import ru.erp.sfsb.service.OrderService;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl extends AbstractService<OrderDto, Order, OrderRepository, OrderMapper>
        implements OrderService {

    private final FileService fileService;

    public OrderServiceImpl(FileService fileService, OrderMapper mapper, OrderRepository repository) {
        super(mapper, repository, "Order");
        this.fileService = fileService;
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public OrderDto addFileToOrder(Long id, MultipartFile file) {
        var order = get(id);
        var fileDto = fileService.save(file);
        order.getFiles().add(fileDto);
        return save(order);
    }
}