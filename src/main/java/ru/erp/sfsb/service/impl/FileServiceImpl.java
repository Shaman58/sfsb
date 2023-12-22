package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.erp.sfsb.dto.FileDto;
import ru.erp.sfsb.mapper.FileMapper;
import ru.erp.sfsb.model.File;
import ru.erp.sfsb.repository.FileRepository;
import ru.erp.sfsb.service.FileService;
import ru.erp.sfsb.service.OrderService;
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

    public FileServiceImpl(FileMapper mapper, FileRepository repository, FileServerUtil fileServerUtil, FileRepository fileRepository, OrderService orderService) {
        super(mapper, repository, "File");
        this.fileServerUtil = fileServerUtil;
        this.fileRepository = fileRepository;
        this.mapper = mapper;
        this.orderService = orderService;
    }

    @Override
    public List<FileDto> getFilesByOrderId(Long orderId) {
        log.info("Looking all files by order id in DB");
        return fileRepository.getFilesByOrderId(orderId).stream().map(mapper::toDto).collect(toList());
    }

    @Override
    public FileDto save(MultipartFile file) {
        log.info("Save file in DB");
        var link = fileServerUtil.saveMultipart(file);
        var filename = file.getOriginalFilename();
        return save(new FileDto(filename, link));
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting file with id {} in DB", id);
        var filename = get(id).getFilename();
        fileServerUtil.deleteMultipart(filename);
        repository.removeById(id);
    }

    @Override
    public FileDto addFileToOrder(Long id, MultipartFile file) {
        var order = orderService.get(id);
        var fileDto = save(file);
        order.getFiles().add(fileDto);
        orderService.save(order);
        return fileDto;
    }
}