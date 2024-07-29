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
import ru.erp.sfsb.dto.MaterialDensityTemplateDto;
import ru.erp.sfsb.service.MaterialDensityTemplateService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/material-density-template")
public class MaterialDensityTemplateController {

    private final MaterialDensityTemplateService materialService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public MaterialDensityTemplateDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return materialService.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<MaterialDensityTemplateDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "9999") @Min(1) @Max(Integer.MAX_VALUE) Integer limit) {
        return materialService.getAll(PageRequest.of(offset, limit));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page")
    public Page<MaterialDensityTemplateDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                         @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return materialService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public MaterialDensityTemplateDto save(@RequestBody @Valid MaterialDensityTemplateDto materialDto) {
        return materialService.save(materialDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public MaterialDensityTemplateDto update(@RequestBody @Valid MaterialDensityTemplateDto materialDto,
                                             @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        materialDto.setId(id);
        return materialService.update(materialDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        materialService.delete(id);
    }
}