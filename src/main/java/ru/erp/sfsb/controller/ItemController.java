package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.service.ItemService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping()
    public ResponseEntity<List<ItemDto>> getAll() {
        return ResponseEntity.ok().body(itemService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(itemService.get(id));
    }

    @PostMapping()
    public ResponseEntity<ItemDto> save(@RequestBody ItemDto itemDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/item").toUriString());
        return ResponseEntity.created(uri).body(itemService.save(itemDto));
    }
}