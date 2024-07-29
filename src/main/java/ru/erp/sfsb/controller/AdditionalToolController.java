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
import ru.erp.sfsb.dto.AdditionalToolDto;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.service.AdditionalToolService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/additional")
public class AdditionalToolController {

    private final AdditionalToolService additionalToolService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public AdditionalToolDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return additionalToolService.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<AdditionalToolDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return additionalToolService.getAll(PageRequest.of(offset, limit));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page")
    public Page<AdditionalToolDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                      @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return additionalToolService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public AdditionalToolDto save(@RequestBody @Valid AdditionalToolDto additionalToolDto) {
        return additionalToolService.save(additionalToolDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public AdditionalToolDto update(@RequestBody @Valid AdditionalToolDto additionalToolDto,
                                    @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        additionalToolDto.setId(id);
        return additionalToolService.update(additionalToolDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        additionalToolService.delete(id);
    }
}