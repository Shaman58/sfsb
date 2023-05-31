package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.service.OrderService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<OrderDto>> getAll() {
        return ResponseEntity.ok().body(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.get(id));
    }

    @PostMapping()
    public ResponseEntity<OrderDto> save(@RequestBody OrderDto orderDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/order").toUriString());
        return ResponseEntity.created(uri).body(orderService.save(orderDto));
    }
}