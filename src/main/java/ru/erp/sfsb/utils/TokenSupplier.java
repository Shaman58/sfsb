package ru.erp.sfsb.utils;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.LogTag;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenSupplier {

    private final Keycloak keycloak;
    private final static LogTag LOG_TAG = LogTag.TOKEN_SUPPLIER;
    private AccessTokenResponse tokenCash;
    long issuedAt;

    public String getToken() {
        if (tokenCash.getExpiresIn() + issuedAt <= LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)) {
            log.info("[{}] Токен протух. Идем за новым", LOG_TAG);
            initCash();
        } else {
            log.info("[{}] Токен не протух. Берем из кэша", LOG_TAG);
        }
        return tokenCash.getToken();
    }

    @PostConstruct
    private void initCash() {
        log.info("[{}] Инициализируем токен в кэш", LOG_TAG);
        tokenCash = keycloak.tokenManager().getAccessToken();
        issuedAt = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }
}