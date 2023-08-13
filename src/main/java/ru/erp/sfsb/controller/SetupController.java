package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
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

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<SetupDto> getAll() {
        return setupService.getAll();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public SetupDto get(@PathVariable Long id) {
        return setupService.get(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/technology/{id}")
    public List<SetupDto> getTechnologySetups(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        return setupService.getTechnologySetups(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public SetupDto save(@RequestBody @Valid SetupDto setupDto) {
        return setupService.save(setupDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public SetupDto update(@RequestBody @Valid SetupDto setupDto,
                           @PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        setupDto.setId(id);
        return setupService.update(setupDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) @Max(Long.MAX_VALUE) Long id) {
        setupService.delete(id);
    }
}