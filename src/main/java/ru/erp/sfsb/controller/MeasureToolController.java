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
import ru.erp.sfsb.dto.MeasureToolDto;
import ru.erp.sfsb.service.MeasureToolService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/measure")
public class MeasureToolController {

    private final MeasureToolService measureToolService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть инструмент измерения по ID")
    @GetMapping("/{id}")
    public MeasureToolDto get(@PathVariable Long id) {
        return measureToolService.get(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все инструменты измерения")
    @GetMapping()
    public List<MeasureToolDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit,
            @RequestParam(value = "filter", defaultValue = "", required = false) String filter) {
        return measureToolService.getByFilter(filter, PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все инструменты измерения")
    @GetMapping("/page")
    public Page<MeasureToolDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                             @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return measureToolService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавить новый инструмент измерения")
    @PostMapping()
    public MeasureToolDto save(@RequestBody @Valid MeasureToolDto measureToolDto) {
        return measureToolService.save(measureToolDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить сведения о инструменте измерения")
    @PutMapping("/{id}")
    public MeasureToolDto update(@RequestBody @Valid MeasureToolDto measureToolDto,
                                 @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        measureToolDto.setId(id);
        return measureToolService.update(measureToolDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить инструмент измерения")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        measureToolService.delete(id);
    }
}