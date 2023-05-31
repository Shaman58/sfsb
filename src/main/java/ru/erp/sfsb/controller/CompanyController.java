package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.CompanyDto;
import ru.erp.sfsb.service.CompanyService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping()
    public ResponseEntity<List<CompanyDto>> getAll() {
        return ResponseEntity.ok().body(companyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(companyService.get(id));
    }

    @PostMapping()
    public ResponseEntity<CompanyDto> save(@RequestBody CompanyDto companyDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/company").toUriString());
        return ResponseEntity.created(uri).body(companyService.save(companyDto));
    }
}