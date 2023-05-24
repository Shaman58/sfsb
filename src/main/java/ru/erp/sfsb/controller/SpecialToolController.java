package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.SpecialToolDto;
import ru.erp.sfsb.service.SpecialToolService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/special")
@RequiredArgsConstructor
public class SpecialToolController {

    private final SpecialToolService specialToolService;

    @GetMapping()
    public ResponseEntity<List<SpecialToolDto>> getAll() {
        return ResponseEntity.ok().body(specialToolService.getAll());
    }

    @PostMapping()
    public ResponseEntity<SpecialToolDto> save(@RequestBody SpecialToolDto specialToolDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/special").toUriString());
        return ResponseEntity.created(uri).body(specialToolService.save(specialToolDto));
    }
}