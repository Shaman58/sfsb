package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.erp.sfsb.dto.FileDto;
import ru.erp.sfsb.service.FileService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {

    private final FileService fileService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order/{orderId}")
    public List<FileDto> getAllByOrderId(@PathVariable Long orderId) {
        return fileService.getFilesByOrderId(orderId);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/order/{id}")
    public FileDto saveFileToOrder(@PathVariable Long id, @RequestBody MultipartFile file,
                                   @AuthenticationPrincipal Jwt jwt) {
        return fileService.addFileToOrder(id, file, jwt);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fileService.delete(id);
    }
}