package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.CustomerDto;
import ru.erp.sfsb.service.CustomerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CustomerDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return customerService.get(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<CustomerDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return customerService.getAll(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page")
    public Page<CustomerDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                          @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return customerService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public CustomerDto save(@RequestBody CustomerDto customerDto) {
        return customerService.save(customerDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CustomerDto update(@RequestBody @Valid CustomerDto customerDto,
                              @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        customerDto.setId(id);
        return customerService.update(customerDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        customerService.delete(id);
    }
}