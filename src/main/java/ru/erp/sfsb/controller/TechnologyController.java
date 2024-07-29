package ru.erp.sfsb.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все технологии")
    @GetMapping()
    public List<TechnologyDto> getAll(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                      @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return technologyService.getAll(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все технологии")
    @GetMapping("/page")
    public Page<TechnologyDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return technologyService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть технологию по ID")
    @GetMapping("/{id}")
    public TechnologyDto get(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return technologyService.get(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Изменить технологию")
    @PutMapping("/{id}")
    public TechnologyDto update(@RequestBody @Valid TechnologyDto technologyDto,
                                @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id,
                                @AuthenticationPrincipal Jwt jwt) {
        technologyDto.setId(id);
        return technologyService.update(technologyDto, jwt);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Запретить доступ к технологии")
    @GetMapping("/{id}/block")
    public void block(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id,
                      @AuthenticationPrincipal Jwt jwt) {
        technologyService.block(id, jwt);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Разрешить доступ к технологии")
    @GetMapping("/{id}/unblock")
    public void unblock(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id,
                        @AuthenticationPrincipal Jwt jwt) {
        technologyService.unblock(id, jwt);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Установить флаг 'просчитано'")
    @GetMapping("/calculate/{id}")
    public TechnologyDto setCalculated(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt, @RequestParam boolean isComputed) {
        return technologyService.setComputed(id, jwt, isComputed);
    }
}