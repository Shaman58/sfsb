package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
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
    public List<TechnologyDto> getAll() {
        return technologyService.getAll();
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