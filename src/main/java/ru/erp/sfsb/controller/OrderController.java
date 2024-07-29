package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<OrderDto> getAll(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                 @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return orderService.getAll(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page")
    public Page<OrderDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                       @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return orderService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find")
    public List<OrderDto> getAllByQuery(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                        @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit,
                                        @RequestParam(value = "search", defaultValue = "") String search) {
        return orderService.getAllByQuery(search, PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public OrderDto get(@PathVariable Long id) {
        return orderService.get(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public OrderDto save(@RequestBody @Valid OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public OrderDto update(@RequestBody @Valid OrderDto orderDto,
                           @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        orderDto.setId(id);
        return orderService.update(orderDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        orderService.delete(id);
    }
}