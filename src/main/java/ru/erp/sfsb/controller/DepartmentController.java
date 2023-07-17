package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.DepartmentDto;
import ru.erp.sfsb.service.DepartmentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public DepartmentDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return departmentService.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<DepartmentDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return departmentService.getAll(PageRequest.of(offset, limit));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/company/{id}")
    public List<DepartmentDto> getAllByCompanyId(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit,
            @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return departmentService.getCompanyDepartments(id, PageRequest.of(offset, limit));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public DepartmentDto save(@RequestBody @Valid DepartmentDto departmentDto) {
        return departmentService.save(departmentDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public DepartmentDto update(@RequestBody @Valid DepartmentDto departmentDto,
                                @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        departmentDto.setId(id);
        return departmentService.update(departmentDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        departmentService.delete(id);
    }
}