package ru.erp.sfsb.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.erp.sfsb.dto.CommercialProposalDto;

@Component
@Slf4j
public class CpStoreServerUtil {

    private final WebClient cpStoreWebClient;

    public CpStoreServerUtil(@Qualifier("cpStoreWebClient") WebClient cpStoreWebClient) {
        this.cpStoreWebClient = cpStoreWebClient;
    }

    public void uploadCp(CommercialProposalDto cp) {
        cpStoreWebClient.post()
                .bodyValue(cp)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(log::info);
    }
}