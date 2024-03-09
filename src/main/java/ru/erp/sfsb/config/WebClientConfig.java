package ru.erp.sfsb.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class WebClientConfig {

    @Value("${webclient.file-service.file-url}")
    private String fileApiBaseUrl;
    @Value("${webclient.cp-store.url}")
    private String cpStoreUrl;
    private final AccessTokenResponse accessTokenResponse;

    @Bean(name = "fileApiWebClient")
    public WebClient fileApiWebClient() {
        return WebClient.builder()
                .baseUrl(fileApiBaseUrl)
                .build();
    }

    @Bean(name = "cpStoreWebClient")
    public WebClient cpStoreWebClient() {
        return WebClient.builder()
                .baseUrl(cpStoreUrl)
                .defaultHeader("Authorization", "bearer " + accessTokenResponse.getToken())
                .build();
    }
}