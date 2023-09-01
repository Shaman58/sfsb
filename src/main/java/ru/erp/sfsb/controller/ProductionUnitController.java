package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.ProductionUnitDto;
import ru.erp.sfsb.service.ProductionUnitService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/unit")
public class ProductionUnitController {

    private final ProductionUnitService productionUnitService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductionUnitDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return productionUnitService.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<ProductionUnitDto> getAll() {
        return productionUnitService.getAll();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ProductionUnitDto save(@RequestBody @Valid ProductionUnitDto productionUnitDto) {
        return productionUnitService.save(productionUnitDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ProductionUnitDto update(@RequestBody @Valid ProductionUnitDto productionUnitDto,
                                    @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        productionUnitDto.setId(id);
        return productionUnitService.save(productionUnitDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        productionUnitService.delete(id);
    }
}