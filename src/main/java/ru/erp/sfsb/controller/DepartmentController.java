package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.DepartmentDto;
import ru.erp.sfsb.service.DepartmentService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getAll() {
        return ResponseEntity.ok().body(departmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(departmentService.get(id));
    }

    @PostMapping()
    public ResponseEntity<DepartmentDto> save(@RequestBody DepartmentDto departmentDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/department").toUriString());
        return ResponseEntity.created(uri).body(departmentService.save(departmentDto));
    }
}