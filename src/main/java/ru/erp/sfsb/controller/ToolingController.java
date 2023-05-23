package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.ToolingDto;
import ru.erp.sfsb.service.ToolingService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tooling")
public class ToolingController {

    private final ToolingService toolingService;

    @GetMapping()
    public ResponseEntity<List<ToolingDto>> getAll() {
        return ResponseEntity.ok().body(toolingService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToolingDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(toolingService.get(id));
    }

    @PostMapping()
    public ResponseEntity<ToolingDto> save(@RequestBody ToolingDto toolingDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/tooling").toUriString());
        return ResponseEntity.created(uri).body(toolingService.save(toolingDto));
    }
}