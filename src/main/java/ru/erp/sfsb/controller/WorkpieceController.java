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
import ru.erp.sfsb.dto.WorkpieceDto;
import ru.erp.sfsb.service.WorkpieceService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/workpiece")
public class WorkpieceController {

    private final WorkpieceService workpieceService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть заготовку по ID")
    @GetMapping("/{id}")
    public WorkpieceDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return workpieceService.get(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все заготовки")
    @GetMapping()
    public List<WorkpieceDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return workpieceService.getAll(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все заготовки")
    @GetMapping("/page")
    public Page<WorkpieceDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                           @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return workpieceService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавить новую заготовку")
    @PostMapping()
    public WorkpieceDto save(@RequestBody @Valid WorkpieceDto workpieceDto) {
        return workpieceService.save(workpieceDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Изменить заготовку")
    @PutMapping("/{id}")
    public WorkpieceDto update(@RequestBody @Valid WorkpieceDto workpieceDto,
                               @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        workpieceDto.setId(id);
        return workpieceService.update(workpieceDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить заготовку")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        workpieceService.delete(id);
    }
}