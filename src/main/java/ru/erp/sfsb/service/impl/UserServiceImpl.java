package ru.erp.sfsb.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.erp.sfsb.LogTag;
import ru.erp.sfsb.dto.UserDto;
import ru.erp.sfsb.exception.KeycloakOtherException;
import ru.erp.sfsb.exception.KeycloakUserConflictException;
import ru.erp.sfsb.service.UserService;
import ru.erp.sfsb.utils.FileServerUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${webclient.file-service.file-url}")
    private String fileExternalUrl;
    private final UsersResource usersResource;
    private final RolesResource rolesResource;
    private final FileServerUtil fileServerUtil;
    private final static LogTag LOG_TAG = LogTag.USER_SERVICE;
    private static int counter =0;

    @Override
    public UserDto save(UserDto user) {
        log.info("[{}] Создание пользователя в KeyCloak БД", LOG_TAG);
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new KeycloakOtherException(
                    String.format("[%s] При создании пользователя пароль должен быть задан", LOG_TAG));
        }
        var credentials = createCredentials(user.getPassword());
        var kcUser = new UserRepresentation();
        kcUser.setUsername(user.getUsername());
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setCredentials(List.of(credentials));
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);
        var response = usersResource.create(kcUser);
        log.info("[{}] Ответ от KeyCloak = {}", LOG_TAG, response.getStatusInfo());
        throwException(response.getStatus());
        var uuid = CreatedResponseUtil.getCreatedId(response);
        log.info("[{}] Добавление роли пользователю", LOG_TAG);
        usersResource.get(uuid).roles().realmLevel().add(getRoleRepresentations(user.getRoles()));
        return get(uuid);
    }

    @Override
    public UserDto update(String uuid, UserDto user) {
        log.info("[{}] Обновление пользователя в KeyCloak БД", LOG_TAG);
        var kcUser = new UserRepresentation();
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        usersResource.get(uuid).update(kcUser);
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            log.info("[{}] Обновление пароля пользователя", LOG_TAG);
            usersResource.get(uuid).resetPassword(createCredentials(user.getPassword()));
        }
        usersResource.get(uuid).roles().realmLevel().remove(getRoleRepresentations(getRoles()));
        usersResource.get(uuid).roles().realmLevel().add(getRoleRepresentations(user.getRoles()));
        return get(uuid);
    }

    @Override
    public List<UserDto> getAll() {
        log.info("[{}] Получение всех профилей пользователей из KeyCloak БД", LOG_TAG);
        return usersResource.list()
                .stream()
                .map(this::repToUserDto)
                .toList();
    }

    @Override
    public UserDto get(String uuid) {
        log.info("[{}] Получение профиля пользователя c uuid={} из KeyCloak БД", LOG_TAG, uuid);
        System.out.println("counter: "+counter++);
        try {
            return repToUserDto(usersResource.get(uuid).toRepresentation());
        } catch (Exception e) {
            log.warn("[{}] Профиль пользователя c uuid={} не обнаружен в KeyCloak БД", LOG_TAG, uuid);
            throw new KeycloakOtherException(
                    String.format("[%s] Сервер Keycloak недоступен или пользователь c uuid=%s отсутсвует", LOG_TAG, uuid));
        }
    }

    @Override
    @Cacheable("users")
    public UserDto getOrNullObject(String uuid) {
        log.info("[{}] Получение профиля пользователя c uuid={} из KeyCloak БД", LOG_TAG, uuid);
        try {
            return repToUserDto(usersResource.get(uuid).toRepresentation());
        } catch (Exception e) {
            log.warn("[{}] Профиль пользователя c uuid={} не обнаружен в KeyCloak БД", LOG_TAG, uuid);
            return buildUser(uuid);
        }
    }

    @Override
    public List<String> getRoles() {
        log.info("[{}] Получение всех ролей из KeyCloak БД", LOG_TAG);
        return rolesResource.list().stream()
                .filter(role -> !role.getDescription().startsWith("$"))
                .map(RoleRepresentation::getName)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void delete(String uuid) {
        log.info("[{}] Удаление профиля с uuid={} из KeyCloak БД", LOG_TAG, uuid);
        var response = usersResource.delete(uuid).getStatus();
        System.out.println(response);
        if (response != HttpStatus.NO_CONTENT.value()) {
            throw new KeycloakOtherException(
                    String.format("[%s] Ошибка удаления пользователя!", LOG_TAG));
        }
    }

    @Override
    public void setAttribute(String uuid, Map<String, List<String>> attributes) {
        var userRepresentation = usersResource.get(uuid).toRepresentation();
        userRepresentation.setAttributes(attributes);
        log.info("[{}] Сохранение атрибутов профиля пользователя в KeyCloak БД", LOG_TAG);
        usersResource.get(uuid).update(userRepresentation);
    }

    @Override
    public Map<String, List<String>> getAttributes(String uuid) {
        log.info("[{}] Получение атрибутов профиля пользователя из KeyCloak БД", LOG_TAG);
        return usersResource.get(uuid).toRepresentation()
                .getAttributes();

    }

    @Override
    public void setPicture(String uuid, MultipartFile file) {
        log.info("[{}] Сохранение аватара в профиль пользователя KeyCloak БД", LOG_TAG);
        if (file == null) {
            throw new KeycloakOtherException(
                    String.format("[%s] Файл не должен быть пустой", LOG_TAG));
        }
        var attributes = getAttributes(uuid);
        String link;
        if (attributes != null) {
            link = attributes.get("picture").get(0);
            fileServerUtil.deleteMultipart(link);
        }
        link = fileServerUtil.saveMultipart(file);

        setAttribute(uuid, Map.of("picture", List.of(link)));
    }

    private List<String> getUserRoles(String uuid) {
        var roles = usersResource.get(uuid).roles().realmLevel().listAll();
        return roles.stream()
                .map(RoleRepresentation::getName)
                .toList();
    }

    private List<RoleRepresentation> getRoleRepresentations(List<String> roles) {
        log.info(roles.toString());
        return roles.stream()
                .map(role -> rolesResource.get(role).toRepresentation())
                .toList();
    }

    private void throwException(int status) {
        if (status != 201) {
            if (status == 409) {
                throw new KeycloakUserConflictException(
                        String.format("[%s] Пользователь с такими данными уже существует", LOG_TAG));
            }
            throw new KeycloakOtherException(
                    String.format("[%s] Ошибка создания пользователя", LOG_TAG));
        }
    }

    private CredentialRepresentation createCredentials(String password) {
        var credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        return credentialRepresentation;
    }

    private UserDto repToUserDto(UserRepresentation userRepresentation) {
        var user = new UserDto();
        user.setId(userRepresentation.getId());
        user.setUsername(userRepresentation.getUsername());
        user.setEmail(userRepresentation.getEmail());
        user.setFirstName(userRepresentation.getFirstName());
        user.setLastName(userRepresentation.getLastName());
        user.setRoles(getUserRoles(userRepresentation.getId()));
        if (userRepresentation.getAttributes() != null) {
            user.setPicture(String.format("%s?filename=%s", fileExternalUrl,
                    userRepresentation.getAttributes().get("picture").get(0)));
        }
        return user;
    }

    private UserDto buildUser(String uuid) {
        UserDto user = new UserDto();
        user.setId(uuid);
        return user;
    }
}