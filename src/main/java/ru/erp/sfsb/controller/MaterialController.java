package ru.erp.sfsb.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.MaterialDto;
import ru.erp.sfsb.model.Geometry;
import ru.erp.sfsb.service.impl.MaterialServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/material")
public class MaterialController {

    private final MaterialServiceImpl materialService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть материал по ID")
    @GetMapping("/{id}")
    public MaterialDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return materialService.get(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все материалы")
    @GetMapping()
    public List<MaterialDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "9999") @Min(1) @Max(Integer.MAX_VALUE) Integer limit,
            @RequestParam(value = "filter", defaultValue = "", required = false) String filter,
            @RequestParam(value = "geometry", required = false) Geometry geometry) {
        return materialService.getByFilter(filter, geometry, PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все материалы")
    @GetMapping("/page")
    public Page<MaterialDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                          @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return materialService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все материалы без цены")
    @GetMapping("/no-cost")
    public List<MaterialDto> getAllWithoutPrice() {
        return materialService.getMaterialWithoutPrice();
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все материалы с истекшим сроком действия")
    @GetMapping("/date-expired")
    public List<MaterialDto> getAllWithUpdateExpired() {
        return materialService.getMaterialWithExpiredDate();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавить новый материал")
    @PostMapping()
    public MaterialDto save(@RequestBody @Valid MaterialDto materialDto) {
        return materialService.save(materialDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить сведения о материале")
    @PutMapping("/{id}")
    public MaterialDto update(@RequestBody @Valid MaterialDto materialDto,
                              @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        materialDto.setId(id);
        return materialService.update(materialDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить цену на материал")
    @PutMapping("/{id}/price")
    public MaterialDto updatePrice(@RequestBody @Valid MaterialDto materialDto,
                                   @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        materialDto.setId(id);
        return materialService.updatePrice(materialDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить материал")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        materialService.delete(id);
    }
}