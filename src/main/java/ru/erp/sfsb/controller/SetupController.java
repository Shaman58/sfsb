package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.SetupDto;
import ru.erp.sfsb.service.SetupService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/setup")
public class SetupController {

    private final SetupService setupService;

    @GetMapping()
    public ResponseEntity<List<SetupDto>> getAll() {
        return ResponseEntity.ok().body(setupService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetupDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(setupService.get(id));
    }

    @PostMapping()
    public ResponseEntity<SetupDto> save(@RequestBody SetupDto setupDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/setup").toUriString());
        return ResponseEntity.created(uri).body(setupService.save(setupDto));
    }
}