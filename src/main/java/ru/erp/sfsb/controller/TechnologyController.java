package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.service.TechnologyService;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/technology")
public class TechnologyController {

    private final TechnologyService technologyService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<TechnologyDto> getAll(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                      @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return technologyService.getAll(PageRequest.of(offset, limit));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public TechnologyDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return technologyService.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public TechnologyDto update(@RequestBody @Valid TechnologyDto technologyDto,
                                @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        technologyDto.setId(id);
        technologyDto.setComputed(false);
        return technologyService.update(technologyDto);
    }
}