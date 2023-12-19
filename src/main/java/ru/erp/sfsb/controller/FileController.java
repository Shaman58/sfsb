package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/order/{id}")
    public void saveFileToOrder(@PathVariable Long id, @RequestBody MultipartFile file) {
        fileService.addFileToOrder(id, file);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fileService.delete(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PutMapping("/user/{uuid}")
    public void savePictureToUser(@PathVariable String uuid, MultipartFile file) {
        fileService.setPicture(uuid, file);
    }
}