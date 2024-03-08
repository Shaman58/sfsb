package ru.erp.sfsb.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.erp.sfsb.dto.CommercialProposalDto;

@Component
@RequiredArgsConstructor
@Slf4j
public class CpStoreServerUtil {

    @Value("${webclient.cp-store.url}")
    private String cpStoreUrl;
    private final WebClient webClient;

    private void uploadCp(CommercialProposalDto cp) {
        webClient.post()
                .uri(cpStoreUrl)
                .bodyValue(cp)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(log::info);
    }
}