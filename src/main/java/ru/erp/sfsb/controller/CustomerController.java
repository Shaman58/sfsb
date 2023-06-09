package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.erp.sfsb.dto.CustomerDto;
import ru.erp.sfsb.service.CustomerService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAll() {
        return ResponseEntity.ok().body(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(customerService.get(id));
    }

    @PostMapping()
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto customerDto) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/customer").toUriString());
        return ResponseEntity.created(uri).body(customerService.save(customerDto));
    }
}