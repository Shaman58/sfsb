package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.ProductionAreaDto;
import ru.erp.sfsb.service.ProductionAreaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/area")
public class ProductionAreaController {

    private final ProductionAreaService productionAreaService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/company/{id}")
    public List<ProductionAreaDto> getAllByCompany(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return productionAreaService.getAllByCompanyId(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductionAreaDto get(@PathVariable Long id) {
        return productionAreaService.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ProductionAreaDto save(@RequestBody @Valid ProductionAreaDto productionAreaDto) {
        return productionAreaService.save(productionAreaDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ProductionAreaDto update(@RequestBody @Valid ProductionAreaDto productionAreaDto,
                                    @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        productionAreaDto.setId(id);
        return productionAreaService.update(productionAreaDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        productionAreaService.delete(id);
    }
}