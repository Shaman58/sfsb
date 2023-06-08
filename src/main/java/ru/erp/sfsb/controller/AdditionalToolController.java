package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.AdditionalToolDto;
import ru.erp.sfsb.service.AdditionalToolService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/additional")
@RequiredArgsConstructor
public class AdditionalToolController {

    private final AdditionalToolService additionalToolService;

    @GetMapping()
    public ResponseEntity<List<AdditionalToolDto>> getAll() {
        return ResponseEntity.ok().body(additionalToolService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdditionalToolDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(additionalToolService.get(id));
    }

    @PostMapping()
    public ResponseEntity<AdditionalToolDto> save(@RequestBody AdditionalToolDto additionalToolDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/additional").toUriString());
        return ResponseEntity.created(uri).body(additionalToolService.save(additionalToolDto));
    }
}