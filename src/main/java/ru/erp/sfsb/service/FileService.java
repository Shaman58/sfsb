package ru.erp.sfsb.service;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.multipart.MultipartFile;
import ru.erp.sfsb.dto.FileDto;

import java.util.List;

public interface FileService extends Service<FileDto> {

    FileDto save(MultipartFile file, Jwt jwt);

    FileDto update(Long id, MultipartFile file, Jwt jwt);

    List<FileDto> getFilesByOrderId(Long orderId);

    FileDto addFileToOrder(Long id, MultipartFile file, Jwt jwt);

    FileDto addFileToCompany(Long id, MultipartFile file, Jwt jwt);
}