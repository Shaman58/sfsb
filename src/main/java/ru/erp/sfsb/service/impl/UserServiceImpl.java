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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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

    @Override
    public UserDto save(UserDto user) {
        log.info("Create user in KC DB");
        if (user.getPassword() == null || user.getPassword().length() == 0) {
            throw new KeycloakOtherException("При создании пользователя пароль должен быть задан");
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
        log.info("response = {}", response.getStatusInfo());
        exceptionThrow(response.getStatus());
        var uuid = CreatedResponseUtil.getCreatedId(response);
        log.info("Add role to user in KC DB");
        usersResource.get(uuid).roles().realmLevel().add(getRoleRepresentations(user.getRoles()));
        return repToUserDto(usersResource.get(uuid).toRepresentation());
    }

    @Override
    public UserDto update(String uuid, UserDto user) {
        log.info("Get user in KC DB");
        var kcUser = new UserRepresentation();
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        log.info("Save user in KC DB");
        usersResource.get(uuid).update(kcUser);
        if (user.getPassword() != null && user.getPassword().length() != 0) {
            log.info("Reset password user in KC DB");
            usersResource.get(uuid).resetPassword(createCredentials(user.getPassword()));
        }
        log.info("Remove roles of user in KC DB");
        usersResource.get(uuid).roles().realmLevel().remove(getRoleRepresentations(getRoles()));
        log.info("Add roles to user in KC DB");
        usersResource.get(uuid).roles().realmLevel().add(getRoleRepresentations(user.getRoles()));
        return repToUserDto(usersResource.get(uuid).toRepresentation());
    }

    @Override
    public List<UserDto> getAll() {
        log.info("Get all user profiles from KC DB");
        return usersResource.list()
                .stream()
                .map(this::repToUserDto)
                .toList();
    }

    @Override
    public UserDto get(String uuid) {
        log.info("Get user profile from KC DB");
        return repToUserDto(usersResource.get(uuid).toRepresentation());
    }

    @Override
    public List<String> getRoles() {
        log.info("Get all roles from KC DB");
        return rolesResource.list().stream()
                .filter(role -> !role.getDescription().startsWith("$"))
                .map(RoleRepresentation::getName)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String uuid) {
        log.info("User deleting");
        var response = usersResource.delete(uuid).getStatus();
        System.out.println(response);
        if (response != HttpStatus.NO_CONTENT.value()) {
            throw new KeycloakOtherException("Ошибка удаления пользователя!");
        }
    }

    @Override
    public void setAttribute(String uuid, Map<String, List<String>> attributes) {
        var userRepresentation = usersResource.get(uuid).toRepresentation();
        userRepresentation.setAttributes(attributes);
        log.info("Save attributes of user in KC DB");
        usersResource.get(uuid).update(userRepresentation);
    }

    @Override
    public Map<String, List<String>> getAttributes(String uuid) {
        return usersResource.get(uuid).toRepresentation()
                .getAttributes();

    }

    @Override
    public void setPicture(String uuid, MultipartFile file) {
        log.info("Set picture");
        if (file == null) {
            throw new KeycloakOtherException("Файл не должен быть пустой");
        }

        var link = getAttributes(uuid).getOrDefault("picture", null).get(0);
        if (link != null && !link.isEmpty()) {
            log.info("Delete old picture");
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

    private void exceptionThrow(int status) {
        if (status != 201) {
            if (status == 409) {
                throw new KeycloakUserConflictException("Пользователь с такими данными уже существует");
            }
            throw new KeycloakOtherException("Ошибка создания пользователя");
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
}