package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.erp.sfsb.dto.FileDto;
import ru.erp.sfsb.exception.FileReadException;
import ru.erp.sfsb.mapper.FileMapper;
import ru.erp.sfsb.model.File;
import ru.erp.sfsb.repository.repos.FileEntityRepository;
import ru.erp.sfsb.service.CompanyService;
import ru.erp.sfsb.service.FileService;
import ru.erp.sfsb.service.OrderService;
import ru.erp.sfsb.service.UserService;
import ru.erp.sfsb.utils.FileServerUtil;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.erp.sfsb.LogTag.FILE_SERVICE;

@Service
@Transactional
@Slf4j
public class FileServiceImpl extends AbstractService<FileDto, File, FileEntityRepository, FileMapper>
        implements FileService {

    private final FileServerUtil fileServerUtil;
    private final FileEntityRepository fileRepository;
    private final FileMapper mapper;
    private final OrderService orderService;
    private final UserService userService;
    private final CompanyService companyService;

    public FileServiceImpl(FileMapper mapper, FileEntityRepository repository, FileServerUtil fileServerUtil, FileEntityRepository fileRepository, OrderService orderService, UserService userService, CompanyService companyService) {
        super(mapper, repository, "File", FILE_SERVICE);
        this.fileServerUtil = fileServerUtil;
        this.fileRepository = fileRepository;
        this.mapper = mapper;
        this.orderService = orderService;
        this.userService = userService;
        this.companyService = companyService;
    }

    @Override
    public List<FileDto> getFilesByOrderId(Long orderId) {
        log.info("[{}] Поиск всех файлов по заказу с id={} in БД", getLogTag(), orderId);
        return fileRepository.getFilesByOrderId(orderId).stream().map(mapper::toDto).collect(toList());
    }

    @Override
    public FileDto save(MultipartFile file, Jwt jwt) {
        log.info("[{}] Сохранение файла в хранилише", getLogTag());
        var link = fileServerUtil.saveMultipart(file);
        var filename = file.getOriginalFilename();
        var uuid = jwt.getClaim("sub").toString();
        var user = userService.get(uuid);
        var fileDto = new FileDto(filename, link, user);
        return save(fileDto);
    }

    @Override
    public FileDto update(Long id, MultipartFile file, Jwt jwt) {
        log.info("[{}] Обновление файла с id={} в хранилище", getLogTag(), id);
        var fileDto = get(id);
        var link = fileServerUtil.saveMultipart(file);
        fileServerUtil.deleteMultipart(fileDto.getLink());
        var filename = file.getOriginalFilename();
        var uuid = jwt.getClaim("sub").toString();
        var user = userService.get(uuid);
        log.info(user.toString());
        fileDto.setUser(user);
        fileDto.setLink(link);
        fileDto.setFilename(filename);
        return update(fileDto);
    }

    @Override
    public void delete(Long id) {
        log.info("[{}] Удаление файла с id={} из хранилища", getLogTag(), id);
        var filename = get(id).getFilename();
        fileServerUtil.deleteMultipart(filename);
        getRepository().removeById(id);
    }

    @Override
    public FileDto addFileToOrder(Long orderId, MultipartFile file, Jwt jwt) {
        log.info("[{}] Добавление файла к заявке с id={}", getLogTag(), orderId);
        checkSize(file);
        var order = orderService.get(orderId);
        var fileDto = save(file, jwt);
        order.getFiles().add(fileDto);
        orderService.save(order);
        return fileDto;
    }

    @Override
    public FileDto addFileToCompany(Long companyId, MultipartFile file, Jwt jwt) {
        log.info("[{}] Добавление файла к компании с id={}", getLogTag(), companyId);
        checkSize(file);
        pictureInfo(file);
        var company = companyService.get(companyId);
        if (company.getLogo() == null) {
            var fileDto = save(file, jwt);
            company.setLogo(fileDto);
            companyService.save(company);
            return fileDto;
        } else {
            return update(company.getLogo().getId(), file, jwt);
        }
    }

    private void pictureInfo(MultipartFile file) {
        try {
            var image = ImageIO.read((file.getInputStream()));
            log.info("[{}] Размер изображения={}x{}", getLogTag(), image.getWidth(), image.getHeight());
        } catch (Exception e) {
            throw new FileReadException(
                    String.format("[%s] Ошибка доступа к файлу", getLogTag()));
        }
    }

    private void checkSize(MultipartFile file) {
        try {
            if (file.getBytes().length == 0) {
                log.error("[{}] Файл не должен быть пустым", getLogTag());
                throw new FileReadException("Файл не должен быть пустым");
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("[%s] Ошибка доступа к файлу", getLogTag()));
        }
    }
}
