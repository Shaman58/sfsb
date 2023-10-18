package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.EmployeeDto;
import ru.erp.sfsb.service.EmployeeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public EmployeeDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return employeeService.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<EmployeeDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return employeeService.getAll(PageRequest.of(offset, limit));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public EmployeeDto save(@RequestBody @Valid EmployeeDto employeeDto) {
        return employeeService.save(employeeDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public EmployeeDto update(@RequestBody @Valid EmployeeDto employeeDto,
                              @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        employeeDto.setId(id);
        return employeeService.update(employeeDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        employeeService.delete(id);
    }
}