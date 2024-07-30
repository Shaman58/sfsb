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
import ru.erp.sfsb.dto.ToolingDto;
import ru.erp.sfsb.service.ToolingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "API взаимодействия с ToolingService")
@RequestMapping("/api/tooling")
public class ToolingController {

    private final ToolingService toolingService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть оснастку по ID")
    @GetMapping("/{id}")
    public ToolingDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return toolingService.get(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все оснастки")
    @GetMapping()
    public List<ToolingDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit,
            @RequestParam(value = "filter", defaultValue = "", required = false) String filter) {
        return toolingService.getByFilter(filter, PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все оснастки")
    @GetMapping("/page")
    public Page<ToolingDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                         @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return toolingService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавить новую оснастку")
    @PostMapping()
    public ToolingDto save(@RequestBody @Valid ToolingDto toolingDto) {
        return toolingService.save(toolingDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Изменить оснастку по ID")
    @PutMapping("/{id}")
    public ToolingDto update(@RequestBody @Valid ToolingDto toolingDto,
                             @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        toolingDto.setId(id);
        return toolingService.update(toolingDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить оснастку по ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        toolingService.delete(id);
    }
}