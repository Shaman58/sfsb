package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.ProductionAreaDto;
import ru.erp.sfsb.dto.ProductionAreaPostDto;
import ru.erp.sfsb.mapper.CustomAreaMapper;
import ru.erp.sfsb.service.ProductionAreaService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/area")
public class ProductionAreaController {

    private final ProductionAreaService productionAreaService;
    private final CustomAreaMapper customAreaMapper;

    @GetMapping()
    public ResponseEntity<List<ProductionAreaDto>> getAll() {
        return ResponseEntity.ok().body(productionAreaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionAreaDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(productionAreaService.get(id));
    }

    @PostMapping()
    public ResponseEntity<ProductionAreaDto> save(@RequestBody ProductionAreaPostDto productionAreaPostDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/area").toUriString());
        return ResponseEntity.created(uri).body(productionAreaService.save(customAreaMapper.convert(productionAreaPostDto)));
    }
}