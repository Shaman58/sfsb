package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.EmployeeDto;
import ru.erp.sfsb.service.EmployeeService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok().body(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(employeeService.get(id));
    }

    @PostMapping()
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto employeeDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/employee").toUriString());
        return ResponseEntity.created(uri).body(employeeService.save(employeeDto));
    }
}