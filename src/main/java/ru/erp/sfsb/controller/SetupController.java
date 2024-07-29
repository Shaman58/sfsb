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
import ru.erp.sfsb.dto.SetupDto;
import ru.erp.sfsb.service.SetupService;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/setup")
public class SetupController {

    private final SetupService setupService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<SetupDto> getAll() {
        return setupService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page")
    public Page<SetupDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                       @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return setupService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public SetupDto get(@PathVariable Long id) {
        return setupService.get(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public SetupDto save(@RequestBody @Valid SetupDto setupDto) {
        return setupService.save(setupDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public SetupDto update(@RequestBody @Valid SetupDto setupDto,
                           @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        setupDto.setId(id);
        return setupService.update(setupDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        setupService.delete(id);
    }
}