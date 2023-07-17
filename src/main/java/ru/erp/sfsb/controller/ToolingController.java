package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.ToolingDto;
import ru.erp.sfsb.service.ToolingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/tooling")
public class ToolingController {

    private final ToolingService toolingService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ToolingDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return toolingService.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<ToolingDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return toolingService.getAll(PageRequest.of(offset, limit));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ToolingDto save(@RequestBody @Valid ToolingDto toolingDto) {
        return toolingService.save(toolingDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ToolingDto update(@RequestBody @Valid ToolingDto toolingDto,
                             @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        toolingDto.setId(id);
        return toolingService.update(toolingDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        toolingService.delete(id);
    }
}