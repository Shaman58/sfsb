package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.erp.sfsb.dto.FileDto;
import ru.erp.sfsb.mapper.FileMapper;
import ru.erp.sfsb.model.File;
import ru.erp.sfsb.repository.FileRepository;
import ru.erp.sfsb.service.CompanyService;
import ru.erp.sfsb.service.FileService;
import ru.erp.sfsb.service.OrderService;
import ru.erp.sfsb.service.UserService;
import ru.erp.sfsb.utils.FileServerUtil;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@Slf4j
public class FileServiceImpl extends AbstractService<FileDto, File, FileRepository, FileMapper>
        implements FileService {

    private final FileServerUtil fileServerUtil;
    private final FileRepository fileRepository;
    private final FileMapper mapper;
    private final OrderService orderService;
    private final UserService userService;
    private final CompanyService companyService;

    public FileServiceImpl(FileMapper mapper, FileRepository repository, FileServerUtil fileServerUtil, FileRepository fileRepository, OrderService orderService, UserService userService, CompanyService companyService) {
        super(mapper, repository, "File");
        this.fileServerUtil = fileServerUtil;
        this.fileRepository = fileRepository;
        this.mapper = mapper;
        this.orderService = orderService;
        this.userService = userService;
        this.companyService = companyService;
    }

    @Override
    public List<FileDto> getFilesByOrderId(Long orderId) {
        log.info("Looking all files by order id in DB");
        return fileRepository.getFilesByOrderId(orderId).stream().map(mapper::toDto).collect(toList());
    }

    @Override
    public FileDto save(MultipartFile file, Jwt jwt) {
        log.info("Save file in DB");
        var link = fileServerUtil.saveMultipart(file);
        var filename = file.getOriginalFilename();
        var uuid = jwt.getClaim("sub").toString();
        var user = userService.get(uuid);
        var fileDto = new FileDto(filename, link, user);
        return save(fileDto);
    }

    @Override
    public FileDto update(Long id, MultipartFile file, Jwt jwt) {
        log.info("Update file in DB");
        var fileDto = get(id);
        var link = fileServerUtil.saveMultipart(file);
        fileServerUtil.deleteMultipart(fileDto.getLink());
        var filename = file.getOriginalFilename();
        var uuid = jwt.getClaim("sub").toString();
        log.info("uuid={}", uuid);
        var user = userService.get(uuid);
        log.info(user.toString());
        fileDto.setUser(user);
        fileDto.setLink(link);
        fileDto.setFilename(filename);
        return update(fileDto);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting file with id {} in DB", id);
        var filename = get(id).getFilename();
        fileServerUtil.deleteMultipart(filename);
        repository.removeById(id);
    }

    @Override
    public FileDto addFileToOrder(Long id, MultipartFile file, Jwt jwt) {
        var order = orderService.get(id);
        var fileDto = save(file, jwt);
        order.getFiles().add(fileDto);
        orderService.save(order);
        return fileDto;
    }

    @Override
    public FileDto addFileToCompany(Long id, MultipartFile file, Jwt jwt) {
        var company = companyService.get(id);
        if (company.getLogo() == null) {
            var fileDto = save(file, jwt);
            company.setLogo(fileDto);
            companyService.save(company);
            return fileDto;
        } else {
            return update(company.getLogo().getId(), file, jwt);
        }
    }
}