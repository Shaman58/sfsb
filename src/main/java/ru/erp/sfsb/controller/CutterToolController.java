package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.CutterToolDto;
import ru.erp.sfsb.service.CutterToolService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cutter")
public class CutterToolController {

    private final CutterToolService cutterToolService;

    @GetMapping()
    public ResponseEntity<List<CutterToolDto>> getAll() {
        return ResponseEntity.ok().body(cutterToolService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CutterToolDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(cutterToolService.get(id));
    }

    @PostMapping()
    public ResponseEntity<CutterToolDto> save(@RequestBody CutterToolDto cutterToolDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/cutter").toUriString());
        return ResponseEntity.created(uri).body(cutterToolService.save(cutterToolDto));
    }
}