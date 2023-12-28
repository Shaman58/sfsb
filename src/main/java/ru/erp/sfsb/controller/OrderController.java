package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.service.OrderService;
import ru.erp.sfsb.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find")
    public List<OrderDto> getAllByQuery(@RequestBody String query) {
        return orderService.getAllByQuery(query);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public OrderDto get(@PathVariable Long id) {
        return orderService.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public OrderDto save(@RequestBody @Valid OrderDto orderDto,
                         @AuthenticationPrincipal Jwt jwt) {
        var uuid = jwt.getClaim("sub").toString();
        var user = userService.get(uuid);
        orderDto.setUser(user);
        return orderService.save(orderDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public OrderDto update(@RequestBody @Valid OrderDto orderDto,
                           @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id,
                           @AuthenticationPrincipal Jwt jwt) {
        var uuid = jwt.getClaim("sub").toString();
        var user = userService.get(uuid);
        orderDto.setId(id);
        orderDto.setUser(user);
        return orderService.update(orderDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        orderService.delete(id);
    }
}