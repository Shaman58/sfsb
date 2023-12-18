package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.erp.sfsb.dto.FileDto;
import ru.erp.sfsb.mapper.FileMapper;
import ru.erp.sfsb.model.File;
import ru.erp.sfsb.repository.FileRepository;
import ru.erp.sfsb.service.FileService;
import ru.erp.sfsb.service.OrderService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@Slf4j
public class FileServiceImpl extends AbstractService<FileDto, File, FileRepository, FileMapper>
        implements FileService {

    private final WebClient webClient;
    private final FileRepository fileRepository;
    private final FileMapper mapper;
    private final OrderService orderService;

    public FileServiceImpl(FileMapper mapper, FileRepository repository, WebClient webClient, FileRepository fileRepository, FileMapper mapper1, OrderService orderService) {
        super(mapper, repository, "File");
        this.webClient = webClient;
        this.fileRepository = fileRepository;
        this.mapper = mapper1;
        this.orderService = orderService;
    }

    @Override
    public FileDto save(MultipartFile file) {
        log.info("Save file in DB");
        var link = saveMultipart(file);
        var filename = file.getOriginalFilename();
        return save(new FileDto(filename, link));
    }

    @Override
    public String saveMultipart(MultipartFile file) {
        log.info("Save file in FS");
        return uploadFile(file)
                .blockOptional()
                .orElseThrow();
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting file with id {} in DB", id);
        var filename = get(id).getFilename();
        repository.removeById(id);
        webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("filename", filename)
                        .build(filename))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public void deleteMultipart(String filename) {
        log.info("Deleting file with name {} in FS", filename);
        webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("filename", filename)
                        .build(filename))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public List<FileDto> getFilesByOrderId(Long orderId) {
        log.info("Looking all files by order id in DB");
        return fileRepository.getFilesByOrderId(orderId).stream().map(mapper::toDto).collect(toList());
    }

    @Override
    public void addFileToOrder(Long id, MultipartFile file) {
        var order = orderService.get(id);
        var fileDto = save(file);
        order.getFiles().add(fileDto);
        orderService.save(order);
    }

    private Mono<String> uploadFile(MultipartFile file) {
        return webClient.post()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", file.getResource()))
                .retrieve()
                .bodyToMono(String.class);
    }
}