package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.WorkpieceDto;
import ru.erp.sfsb.service.WorkpieceService;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/workpiece")
public class WorkpieceController {

    private final WorkpieceService workpieceService;

    @GetMapping()
    public ResponseEntity<List<WorkpieceDto>> getAll() {
        return ResponseEntity.ok().body(workpieceService.getAll());
    }

    @PostMapping()
    public ResponseEntity<WorkpieceDto> save(@RequestBody WorkpieceDto workpieceDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/workpiece").toUriString());
        return ResponseEntity.created(uri).body(workpieceService.save(workpieceDto));
    }
}