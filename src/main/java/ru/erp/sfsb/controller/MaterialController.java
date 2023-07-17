package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.MaterialDto;
import ru.erp.sfsb.service.impl.MaterialServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/material")
public class MaterialController {

    private final MaterialServiceImpl materialService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public MaterialDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return materialService.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<MaterialDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "9999") @Min(1) @Max(Integer.MAX_VALUE) Integer limit) {
        return materialService.getAll(PageRequest.of(offset, limit));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public MaterialDto save(@RequestBody @Valid MaterialDto materialDto) {
        return materialService.save(materialDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public MaterialDto update(@RequestBody @Valid MaterialDto materialDto,
                              @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        materialDto.setId(id);
        return materialService.update(materialDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        materialService.delete(id);
    }
}