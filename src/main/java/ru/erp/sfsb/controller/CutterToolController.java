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
import ru.erp.sfsb.dto.CutterToolDto;
import ru.erp.sfsb.service.CutterToolService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/cutter")
public class CutterToolController {

    private final CutterToolService cutterToolService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CutterToolDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return cutterToolService.get(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<CutterToolDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit,
            @RequestParam(value = "filter", defaultValue = "", required = false) String filter) {
        return cutterToolService.getByFilter(filter, PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page")
    public Page<CutterToolDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return cutterToolService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public CutterToolDto save(@RequestBody @Valid CutterToolDto cutterToolDto) {
        return cutterToolService.save(cutterToolDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CutterToolDto update(@RequestBody @Valid CutterToolDto cutterToolDto,
                                @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        cutterToolDto.setId(id);
        return cutterToolService.update(cutterToolDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        cutterToolService.delete(id);
    }
}