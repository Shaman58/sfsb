package ru.erp.sfsb.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KeycloakConfig {

    @Value("${keycloak.auth-server-url}")
    private String serverURL;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(serverURL)
                .realm(realm)
                .clientId(clientId)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientSecret(clientSecret)
                .build();
    }

    @Bean
    public AccessTokenResponse accessTokenResponse(Keycloak keycloak) {
        return keycloak.tokenManager().getAccessToken();
    }

    @Bean
    public RealmResource realmResource(Keycloak keycloak) {
        return keycloak.realm(realm);
    }

    @Bean
    public UsersResource usersResource(RealmResource realmResource) {
        return realmResource.users();
    }

    @Bean
    public RolesResource rolesResource(RealmResource realmResource) {
        return realmResource.roles();
    }
}