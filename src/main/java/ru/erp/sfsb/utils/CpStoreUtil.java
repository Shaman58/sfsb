package ru.erp.sfsb.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.erp.sfsb.LogTag;
import ru.erp.sfsb.dto.CommercialProposalDto;

@Slf4j
@Component
public class CpStoreUtil {

    private final WebClient cpStoreWebClient;
    private final TokenSupplier tokenSupplier;
    private final static LogTag LOG_TAG = LogTag.CP_STORE_UTIL;

    public CpStoreUtil(@Qualifier("cpStoreWebClient") WebClient cpStoreWebClient, TokenSupplier tokenSupplier) {
        this.cpStoreWebClient = cpStoreWebClient;
        this.tokenSupplier = tokenSupplier;
    }

    public void uploadCp(CommercialProposalDto cp) {
        log.info("[{}] Выполняется выгрузка файла", LOG_TAG);
        cpStoreWebClient.post()
                .header("Authorization", "bearer " + tokenSupplier.getToken())
                .bodyValue(cp)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(log::info);
    }
}