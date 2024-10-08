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
import ru.erp.sfsb.dto.MaterialDensityTemplateDto;
import ru.erp.sfsb.service.MaterialDensityTemplateService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "API взаимодействия с MaterialDensityService")
@RequestMapping("/api/material-density-template")
public class MaterialDensityTemplateController {

    private final MaterialDensityTemplateService materialService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть шаблон плотности материала по ID")
    @GetMapping("/{id}")
    public MaterialDensityTemplateDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return materialService.get(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все шаблоны плотности материалов")
    @GetMapping()
    public List<MaterialDensityTemplateDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "9999") @Min(1) @Max(Integer.MAX_VALUE) Integer limit) {
        return materialService.getAll(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все шаблоны плотности материалов")
    @GetMapping("/page")
    public Page<MaterialDensityTemplateDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                         @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return materialService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавить новый шаблон плотности материала")
    @PostMapping()
    public MaterialDensityTemplateDto save(@RequestBody @Valid MaterialDensityTemplateDto materialDto) {
        return materialService.save(materialDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить сведения о шаблоне плотности материала по ID")
    @PutMapping("/{id}")
    public MaterialDensityTemplateDto update(@RequestBody @Valid MaterialDensityTemplateDto materialDto,
                                             @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        materialDto.setId(id);
        return materialService.update(materialDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить шаблон плотности материала по ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        materialService.delete(id);
    }
}