package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.service.TechnologyService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/technology")
public class TechnologyController {

    private final TechnologyService technologyService;

    @GetMapping()
    public ResponseEntity<List<TechnologyDto>> getAll() {
        return ResponseEntity.ok().body(technologyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnologyDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(technologyService.get(id));
    }

    @PostMapping()
    public ResponseEntity<TechnologyDto> save(@RequestBody TechnologyDto technologyDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/technology").toUriString());
        return ResponseEntity.created(uri).body(technologyService.save(technologyDto));
    }
}