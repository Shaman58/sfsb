package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.ProductionUnitDto;
import ru.erp.sfsb.service.ProductionUnitService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/unit")
public class ProductionUnitController {

    private final ProductionUnitService productionUnitService;

    @GetMapping()
    public ResponseEntity<List<ProductionUnitDto>> getAll() {
        return ResponseEntity.ok().body(productionUnitService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionUnitDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(productionUnitService.get(id));
    }

    @PostMapping()
    public ResponseEntity<ProductionUnitDto> save(@RequestBody ProductionUnitDto productionUnitDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/unit").toUriString());
        return ResponseEntity.created(uri).body(productionUnitService.save(productionUnitDto));
    }
}