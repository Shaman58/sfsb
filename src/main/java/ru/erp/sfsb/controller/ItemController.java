package ru.erp.sfsb.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.service.ItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все предметы")
    @GetMapping()
    public List<ItemDto> getAll(@AuthenticationPrincipal Jwt jwt) {
        return itemService.getAllByTechnologyUser(jwt);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все предметы")
    @GetMapping("/page")
    public Page<ItemDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                      @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return itemService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть предмет по ID")
    @GetMapping("/{id}")
    public ItemDto get(@PathVariable Long id) {
        return itemService.get(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавить новый предмет")
    @PostMapping()
    public ItemDto save(@RequestBody @Valid ItemDto itemDto) {
        return itemService.save(itemDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить сведения о предмете")
    @PutMapping("/{id}")
    public ItemDto update(@RequestBody @Valid ItemDto itemDto,
                          @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        itemDto.setId(id);
        return itemService.update(itemDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить предмет")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        itemService.delete(id);
    }
}