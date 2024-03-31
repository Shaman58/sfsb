package ru.erp.sfsb.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.erp.sfsb.dto.CommercialProposalDto;

@Slf4j
@Component
public class CpStoreServerUtil {

    private final WebClient cpStoreWebClient;
    private final TokenSupplier tokenSupplier;

    public CpStoreServerUtil(@Qualifier("cpStoreWebClient") WebClient cpStoreWebClient, TokenSupplier tokenSupplier) {
        this.cpStoreWebClient = cpStoreWebClient;
        this.tokenSupplier = tokenSupplier;
    }

    public void uploadCp(CommercialProposalDto cp) {
        cpStoreWebClient.post()
                .header("Authorization", "bearer " + tokenSupplier.getToken())
                .bodyValue(cp)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(log::info);
    }
}