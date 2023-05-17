package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.MaterialDto;
import ru.erp.sfsb.service.MaterialService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/material")
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping()
    public ResponseEntity<List<MaterialDto>> getAll() {
        return ResponseEntity.ok().body(materialService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(materialService.get(id));
    }

    @PostMapping()
    public ResponseEntity<MaterialDto> save(@RequestBody MaterialDto materialDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/material").toUriString());
        return ResponseEntity.created(uri).body(materialService.save(materialDto));
    }
}