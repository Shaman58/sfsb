package ru.erp.sfsb.service;

import org.springframework.web.multipart.MultipartFile;
import ru.erp.sfsb.dto.FileDto;

import java.util.List;

public interface FileService extends Service<FileDto> {

    FileDto addFileToOrder(Long id, MultipartFile file);

    List<FileDto> getFilesByOrderId(Long orderId);
}