package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
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

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public OperationDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return service.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<OperationDto> getAll() {
        return service.getAll();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public OperationDto save(@RequestBody @Valid OperationDto operationDto) {
        return service.save(operationDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public OperationDto update(@RequestBody @Valid OperationDto operationDto,
                               @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        operationDto.setId(id);
        return service.update(operationDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        service.delete(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/setup")
    public OperationDto getSetupPrice() {
        return service.getSetupPrice();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/setup")
    public OperationDto updateSetupPrice(@RequestBody @Valid OperationDto operationDto) {
        return service.updateSetupPrice(operationDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/technology")
    public OperationDto getTechnologyPrice() {
        return service.getTechnologyPrice();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/technology")
    public OperationDto updateTechnologyPrice(@RequestBody @Valid OperationDto operationDto) {
        return service.updateTechnologyPrice(operationDto);
    }
}