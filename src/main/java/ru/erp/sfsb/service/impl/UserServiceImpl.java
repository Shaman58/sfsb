//package ru.erp.sfsb.service.impl;
//
//import jakarta.ws.rs.core.Response;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.keycloak.admin.client.CreatedResponseUtil;
//import org.keycloak.admin.client.resource.RolesResource;
//import org.keycloak.admin.client.resource.UsersResource;
//import org.keycloak.representations.idm.CredentialRepresentation;
//import org.keycloak.representations.idm.RoleRepresentation;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.springframework.stereotype.Service;
//import ru.erp.sfsb.dto.EmployeeDto;
//import ru.erp.sfsb.service.UserService;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//
//    private final UsersResource usersResource;
//    private final RolesResource rolesResource;
//
//    @Override
//    public Response createKCUser(EmployeeDto employee) {
//
//        var credentials = createCredentials(employee.getLastName()); //todo fix
//        var kcUser = new UserRepresentation();
//        kcUser.setUsername(employee.getFirstName());//todo fix
//        kcUser.setCredentials(List.of(credentials));
//        kcUser.setEmail(employee.getEmail());
//        kcUser.setEnabled(true);
//        kcUser.setEmailVerified(false);
//        var response = usersResource.create(kcUser);
//        var userId = CreatedResponseUtil.getCreatedId(response);
//        return response;
//    }
//
//    @Override
//    public List<UserRepresentation> getUsers() {
//        return usersResource.list();
//    }
//
//    public void addRoles(String userId, List<String> roles) {
//        var roleRepresentations = roles.stream()
//                .map(role -> rolesResource.get(role).toRepresentation())
//                .toList();
//        var user = usersResource.get(userId);
//        user.roles().realmLevel().add(roleRepresentations);
//    }
//
//    @Override
//    public List<RoleRepresentation> getRoles() {
//        return rolesResource.list()
//                .stream().filter(role -> !role.getDescription().startsWith("$"))
//                .collect(Collectors.toList());
//    }
//
//
//    private CredentialRepresentation createCredentials(String password) {
//        var credentialRepresentation = new CredentialRepresentation();
//        credentialRepresentation.setTemporary(false);
//        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
//        credentialRepresentation.setValue(password);
//        return credentialRepresentation;
//    }
//
//}