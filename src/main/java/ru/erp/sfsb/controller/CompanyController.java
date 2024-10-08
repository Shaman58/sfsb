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
import ru.erp.sfsb.dto.CompanyDto;
import ru.erp.sfsb.service.CompanyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "API взаимодействия с CompanyService")
@Validated
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть компанию по ID")
    @GetMapping("/manager/{id}")
    public CompanyDto get(@PathVariable(required = false) Long id) {
        return companyService.get(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все компании")
    @GetMapping("/manager")
    public List<CompanyDto> getAll() {
        return companyService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все компании")
    @GetMapping("/page")
    public Page<CompanyDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                         @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return companyService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить данные о компании по ID")
    @PutMapping("/manager/{id}")
    public CompanyDto update(@RequestBody @Valid CompanyDto company,
                             @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        company.setId(id);
        return companyService.update(company);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Добавить новую компанию")
    @PostMapping("/manager")
    public CompanyDto save(@RequestBody @Valid CompanyDto company) {
        return companyService.save(company);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить компанию по ID")
    @DeleteMapping("/manager/{id}")
    public void delete(@PathVariable Long id) {
        companyService.delete(id);
    }
}