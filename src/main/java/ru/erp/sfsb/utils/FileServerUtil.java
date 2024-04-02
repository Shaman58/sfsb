package ru.erp.sfsb.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.erp.sfsb.LogTag;
import ru.erp.sfsb.exception.DataTransferException;
import ru.erp.sfsb.exception.ReportGenerateException;

@Slf4j
@RequiredArgsConstructor
@Component
public class FileServerUtil {

    private final WebClient fileApiWebClient;
    private final static LogTag LOG_TAG = LogTag.FILE_SERVER_UTIL;

    public String saveMultipart(MultipartFile file) {
        log.info("[{}] Сохранение файла в FS", LOG_TAG);
        return uploadFile(file)
                .blockOptional()
                .orElseThrow(() -> new DataTransferException(
                        String.format("[%s] Ошибка сохранения файла в FS", LOG_TAG)
                ));
    }

    public void deleteMultipart(String filename) {
        log.info("[{}] Удаление файла {} из FS", LOG_TAG, filename);
        fileApiWebClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("filename", filename)
                        .build(filename))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new ReportGenerateException(String.format("[%s] Файл %s не найден", LOG_TAG, filename))))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new IllegalStateException(String.format("[%s] Ошибка сервера", LOG_TAG))))
                .bodyToMono(Void.class)
                .block();
    }

    public Mono<byte[]> getFile(String filename) {
        log.info("[{}] Запрос файла {} из FS", LOG_TAG, filename);
        return fileApiWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("filename", filename)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new ReportGenerateException(String.format("[%s] Файл %s не найден", LOG_TAG, filename))))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new IllegalStateException(String.format("[%s] Ошибка сервера", LOG_TAG))))
                .toEntity(byte[].class)
                .mapNotNull(ResponseEntity::getBody);
    }

    private Mono<String> uploadFile(MultipartFile file) {
        return fileApiWebClient.post()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", file.getResource()))
                .retrieve()
                .bodyToMono(String.class);
    }
}