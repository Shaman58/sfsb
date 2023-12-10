package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
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
    @GetMapping()
    public CompanyDto get() {
        return companyService.getCompany();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PutMapping()
    public CompanyDto update(@RequestBody @Valid CompanyDto companyDto) {
        return companyService.update(companyDto);
    }
}