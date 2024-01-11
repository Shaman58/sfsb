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

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public CompanyDto getCompany() {
        return companyService.getCompany();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PutMapping()
    public CompanyDto updateCompany(@RequestBody @Valid CompanyDto companyDto) {
        return companyService.updateCompany(companyDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/manager/{id}")
    public CompanyDto get(@PathVariable(required = false) Long id) {
        return companyService.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/manager")
    public List<CompanyDto> getAll() {
        return companyService.getAll();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/manager/{id}")
    public CompanyDto update(@RequestBody @Valid CompanyDto company,
                             @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        company.setId(id);
        return companyService.update(company);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/manager")
    public CompanyDto save(@RequestBody @Valid CompanyDto company) {
        return companyService.save(company);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/manager/{id}")
    public void delete(@PathVariable Long id) {
        companyService.delete(id);
    }
}