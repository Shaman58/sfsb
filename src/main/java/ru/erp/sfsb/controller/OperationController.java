package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.OperationDto;
import ru.erp.sfsb.service.OperationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/operation")
public class OperationController {

    private final OperationService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public OperationDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return service.get(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<OperationDto> getAll() {
        return service.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page")
    public Page<OperationDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                           @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return service.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public OperationDto save(@RequestBody @Valid OperationDto operationDto) {
        return service.save(operationDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public OperationDto update(@RequestBody @Valid OperationDto operationDto,
                               @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        operationDto.setId(id);
        return service.update(operationDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        service.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/setup")
    public OperationDto getSetupPrice() {
        return service.getSetupPrice();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/setup")
    public OperationDto updateSetupPrice(@RequestBody @Valid OperationDto operationDto) {
        return service.updateSetupPrice(operationDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/technology")
    public OperationDto getTechnologyPrice() {
        return service.getTechnologyPrice();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/technology")
    public OperationDto updateTechnologyPrice(@RequestBody @Valid OperationDto operationDto) {
        return service.updateTechnologyPrice(operationDto);
    }
}