package ru.erp.sfsb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.SpecialToolDto;
import ru.erp.sfsb.service.SpecialToolService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "API взаимодействия с SpecialToolService")
@RequestMapping("/api/special")
public class SpecialToolController {

    private final SpecialToolService specialToolService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть спец. инструмент по ID")
    @GetMapping("/{id}")
    public SpecialToolDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return specialToolService.get(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все спец. инструменты")
    @GetMapping()
    public List<SpecialToolDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit,
            @RequestParam(value = "filter", defaultValue = "", required = false) String filter) {
        return specialToolService.getByFilter(filter, PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все спец. инструменты")
    @GetMapping("/page")
    public Page<SpecialToolDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                             @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return specialToolService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавить спец. инструмент")
    @PostMapping()
    public SpecialToolDto save(@RequestBody @Valid SpecialToolDto specialToolDto) {
        return specialToolService.save(specialToolDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Изменить спец. инструмент по ID")
    @PutMapping("/{id}")
    public SpecialToolDto update(@RequestBody @Valid SpecialToolDto specialToolDto,
                                 @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        specialToolDto.setId(id);
        return specialToolService.update(specialToolDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить спец. инструмент по ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        specialToolService.delete(id);
    }
}