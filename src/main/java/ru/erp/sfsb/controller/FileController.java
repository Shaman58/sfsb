package ru.erp.sfsb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.erp.sfsb.dto.FileDto;
import ru.erp.sfsb.service.FileService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "API взаимодействия с FileService")
@RequestMapping("/api/file")
public class FileController {

    private final FileService fileService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть файл по Order ID")
    @GetMapping("/order/{orderId}")
    public List<FileDto> getAllByOrderId(@PathVariable Long orderId) {
        return fileService.getFilesByOrderId(orderId);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все файлы")
    @GetMapping("/page")
    public Page<FileDto> getAllInPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                      @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return fileService.getAllInPage(PageRequest.of(offset, limit));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Сохранить файл в заказ по ID")
    @PostMapping("/order/{id}")
    public FileDto saveFileToOrder(@PathVariable Long id, @RequestBody @NotNull MultipartFile file,
                                   @AuthenticationPrincipal Jwt jwt) {
        return fileService.addFileToOrder(id, file, jwt);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Сохранить файл в компанию по ID")
    @PostMapping("/company/{id}")
    public FileDto saveFileToCompany(@PathVariable Long id, @RequestBody @NotNull MultipartFile file,
                                     @AuthenticationPrincipal Jwt jwt) {
        return fileService.addFileToCompany(id, file, jwt);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить файл по ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fileService.delete(id);
    }
}