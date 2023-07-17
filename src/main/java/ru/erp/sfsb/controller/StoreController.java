package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.StoreDto;
import ru.erp.sfsb.service.StoreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/area/{id}")
    public List<StoreDto> getAllByAreaId(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return storeService.getAllByAreaId(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public StoreDto get(@PathVariable Long id) {
        return storeService.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/area/{id}")
    public StoreDto saveInArea(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id,
                               @RequestBody @Valid StoreDto storeDto) {
        return storeService.saveInArea(storeDto, id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public StoreDto update(@RequestBody @Valid StoreDto storeDto,
                           @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        storeDto.setId(id);
        return storeService.update(storeDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        storeService.delete(id);
    }
}