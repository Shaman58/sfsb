package ru.erp.sfsb.utils;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static ru.erp.sfsb.LogTag.TOKEN_SUPPLIER;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenSupplier {

    private final Keycloak keycloak;
    private AccessTokenResponse tokenCash;

    public String getToken() {
        if (tokenCash.getExpiresIn() > LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(3))) {
            log.info("[{}] Токен протух. Идем за новым", TOKEN_SUPPLIER);
            return keycloak.tokenManager().getAccessToken().getToken();
        } else {
            log.info("[{}] Токен не протух. Берем из кэша", TOKEN_SUPPLIER);
            return tokenCash.getToken();
        }
    }

    @PostConstruct
    private void initCash() {
        log.info("[{}] Инициализируем токен в кэш", TOKEN_SUPPLIER);
        tokenCash = keycloak.tokenManager().getAccessToken();
    }
}