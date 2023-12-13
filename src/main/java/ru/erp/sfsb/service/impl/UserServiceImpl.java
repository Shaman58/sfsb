package ru.erp.sfsb.service.impl;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.UserDto;
import ru.erp.sfsb.exception.KeycloakOtherException;
import ru.erp.sfsb.exception.KeycloakUserConflictException;
import ru.erp.sfsb.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersResource usersResource;
    private final RolesResource rolesResource;

    @Override
    public Response createKCUser(UserDto user) {
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
        exceptionThrow(response.getStatus());
        var userId = CreatedResponseUtil.getCreatedId(response);
        addRoles(userId, user.getRoles());

        return response;
    }

    @Override
    public List<UserDto> getUsers() {
        return usersResource.list()
                .stream()
                .map(this::toUserDto)
                .toList();
    }

    @Override
    public void addRoles(String userId, List<String> roles) {
        var roleRepresentations = roles.stream()
                .map(role -> rolesResource.get(role).toRepresentation())
                .toList();
        var user = usersResource.get(userId);
        user.roles().realmLevel().add(roleRepresentations);
    }

    @Override
    public List<String> getRoles() {
        return rolesResource.list().stream()
                .filter(role -> !role.getDescription().startsWith("$"))
                .map(RoleRepresentation::getName)
                .collect(Collectors.toList());
    }

    public void deleteUser(String id) {

        usersResource.delete(id);
    }

    private void exceptionThrow(int status) {
        if (status != 201) {
            if (status == 409) {
                throw new KeycloakUserConflictException("Пользователь с такими данными уже существует");
            }
            throw new KeycloakOtherException("Пользователь с такими данными уже существует");
        }
    }

    private CredentialRepresentation createCredentials(String password) {
        var credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        return credentialRepresentation;
    }

    private UserDto toUserDto(UserRepresentation userRepresentation) {
        var user = new UserDto();
        user.setId(userRepresentation.getId());
        user.setUsername(userRepresentation.getUsername());
        user.setEmail(userRepresentation.getEmail());
        user.setFirstName(userRepresentation.getFirstName());
        user.setLastName(userRepresentation.getLastName());
        return user;
    }
}