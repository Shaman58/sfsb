package ru.erp.sfsb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.StoreDto;
import ru.erp.sfsb.service.StoreService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping()
    public ResponseEntity<List<StoreDto>> getAll() throws JsonProcessingException {
        return ResponseEntity.ok().body(storeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(storeService.get(id));
    }

    @PostMapping()
    public ResponseEntity<StoreDto> save(@RequestBody StoreDto storeDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/store").toUriString());
        return ResponseEntity.created(uri).body(storeService.save(storeDto));
    }
}