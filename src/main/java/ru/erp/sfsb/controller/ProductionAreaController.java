package ru.erp.sfsb.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.ProductionAreaDto;
import ru.erp.sfsb.service.ProductionAreaService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/area")
public class ProductionAreaController {

    private final ProductionAreaService productionAreaService;

    @GetMapping("/company/{id}")
    public ResponseEntity<List<ProductionAreaDto>> getAllByCompany(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return ResponseEntity.ok().body(productionAreaService.getAllByCompanyId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionAreaDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(productionAreaService.get(id));
    }

    @PostMapping()
    public ResponseEntity<ProductionAreaDto> save(@RequestBody ProductionAreaDto productionAreaDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/area").toUriString());
        return ResponseEntity.created(uri).body(productionAreaService.save(productionAreaDto));
    }
}