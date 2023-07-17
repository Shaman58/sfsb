package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.CompanyDto;
import ru.erp.sfsb.service.CompanyService;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CompanyDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return companyService.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public CompanyDto save(@RequestBody @Valid CompanyDto companyDto) {
        return companyService.save(companyDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public CompanyDto update(@RequestBody @Valid CompanyDto companyDto,
                             @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        companyDto.setId(id);
        return companyService.update(companyDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        companyService.delete(id);
    }
}