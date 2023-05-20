package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.MeasureToolDto;
import ru.erp.sfsb.service.MeasureToolService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/measure")
public class MeasureToolController {

    private final MeasureToolService measureToolService;

    @GetMapping()
    public ResponseEntity<List<MeasureToolDto>> getAll() {
        return ResponseEntity.ok().body(measureToolService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasureToolDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(measureToolService.get(id));
    }

    @PostMapping()
    public ResponseEntity<MeasureToolDto> save(@RequestBody MeasureToolDto measureToolDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/measure").toUriString());
        return ResponseEntity.created(uri).body(measureToolService.save(measureToolDto));
    }
}