package ru.erp.sfsb.service;

import org.springframework.web.multipart.MultipartFile;
import ru.erp.sfsb.dto.FileDto;

import java.util.List;

public interface FileService extends Service<FileDto> {

    FileDto save(MultipartFile file);

    String saveMultipart(MultipartFile file);

    List<FileDto> getFilesByOrderId(Long orderId);

    void deleteMultipart(String filename);

    void addFileToOrder(Long id, MultipartFile file);
}