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
import ru.erp.sfsb.exception.ReportGenerateException;

@Slf4j
@RequiredArgsConstructor
@Component
public class FileServerUtil {

    private final WebClient webClient;

    public String saveMultipart(MultipartFile file) {
        log.info("Save file in FS");
        return uploadFile(file)
                .blockOptional()
                .orElseThrow();
    }

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

    private Mono<String> uploadFile(MultipartFile file) {
        return webClient.post()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", file.getResource()))
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<byte[]> getFile(String uuid) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("filename", uuid)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new ReportGenerateException("File not found: " + uuid)))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new IllegalStateException("Server error retrieving file")))
                .toEntity(byte[].class)
                .mapNotNull(ResponseEntity::getBody);
    }
}