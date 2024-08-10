package ru.erp.sfsb.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.erp.sfsb.LogTag;
import ru.erp.sfsb.dto.report.OrderPlannerData;
import ru.erp.sfsb.exception.ReportGenerateException;

@Slf4j
@Component
public class TaskPlannerUtil {

    private final WebClient orderWebClient;
    private final TokenSupplier tokenSupplier;
    private final static LogTag LOG_TAG = LogTag.CP_STORE_UTIL;

    public TaskPlannerUtil(@Qualifier("taskPlannerWebClient") WebClient orderWebClient, TokenSupplier tokenSupplier) {
        this.orderWebClient = orderWebClient;
        this.tokenSupplier = tokenSupplier;
    }

    public void uploadOrderPlannerData(OrderPlannerData order) {
        log.info("[{}] Выполняется выгрузка OrderPlannerData", LOG_TAG);
        orderWebClient.post()
                .header("Authorization", "bearer " + tokenSupplier.getToken())
                .bodyValue(order)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new ReportGenerateException(String.format("[%s] Сервер недоступен", LOG_TAG))))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new IllegalStateException(String.format("[%s] Ошибка сервера", LOG_TAG))))
                .bodyToMono(String.class)
                .subscribe(log::info);
    }
}

