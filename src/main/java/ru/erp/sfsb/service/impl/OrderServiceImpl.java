package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.dto.UserDto;
import ru.erp.sfsb.mapper.OrderMapper;
import ru.erp.sfsb.model.Order;
import ru.erp.sfsb.repository.repos.OrderEntityRepository;
import ru.erp.sfsb.service.OrderService;
import ru.erp.sfsb.service.UserService;

import java.util.List;

import static ru.erp.sfsb.LogTag.ORDER_SERVICE;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl extends AbstractService<OrderDto, Order, OrderEntityRepository, OrderMapper>
        implements OrderService {

    private final UserService userService;

    public OrderServiceImpl(OrderMapper mapper, OrderEntityRepository repository, UserService userService) {
        super(mapper, repository, "Order", ORDER_SERVICE);
        this.userService = userService;
    }

    @Override
    public List<OrderDto> getAllByQuery(String query, Pageable pageable) {
        log.info("[{}] Поиск всех сущностей типа {} по запросу {} в БД", getLogTag(), getEntityName(), query);
        var orders = getRepository().getOrdersByQueryString(query, pageable.getPageSize(), pageable.getPageNumber());
        var ordersDto = getMapper().toDto(orders);
        ordersDto.forEach(order ->
                order.setItems(filteredItems(order.getItems(), query)));
        return ordersDto;
    }

    @Override
    public OrderDto save(OrderDto order) {
        log.info("[{}] Сохранение сущности типа {} в БД", getLogTag(), getEntityName());
        order.setUser(getAuthUser());
        return getMapper().toDto(getRepository().save(getMapper().toEntity(order)));
    }

    @Override
    public OrderDto update(OrderDto order) {
        log.info("[{}] Обновление сущности типа {} в БД", getLogTag(), getEntityName());
        checkExistById(order.getId());
        return getMapper().toDto(getRepository().save(getMapper().toEntity(order)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("[{}] Удаление сущности типа {} с id={} из БД", getLogTag(), getEntityName(), id);
        Order order = getRepository().findById(id).orElseThrow(() -> getEntityWithIdNotFoundException(id));
        order.getItems().forEach(item -> item.setDeleted(true));
        order.setDeleted(true);
        getRepository().save(order);
    }

    private List<ItemDto> filteredItems(List<ItemDto> items, String query) {
        var filtered = items.stream().filter(
                item -> (
                        item.getTechnology().getDrawingNumber().toLowerCase().contains(query.toLowerCase()) ||
                                item.getTechnology().getDrawingName().toLowerCase().contains(query.toLowerCase())
                )
        ).toList();
        if (filtered.isEmpty()) {
            return items;
        }
        return filtered;
    }

    private UserDto getAuthUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var jwt = (Jwt) authentication.getPrincipal();
        var uuid = jwt.getClaim("sub").toString();
        return userService.get(uuid);
    }
}