package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.EmployeeDto;
import ru.erp.sfsb.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseBody
    @GetMapping("/user")
    public List<UserRepresentation> getAllUsers() {
        return userService.getUsers();
    }

    @ResponseBody
    @GetMapping("/role")
    public List<RoleRepresentation> getAllRoles() {
        return userService.getRoles();
    }

    @ResponseBody
    @PostMapping("/user")
    public ResponseEntity save(@RequestBody EmployeeDto employee) {
        var response = userService.createKCUser(employee);
        return ResponseEntity.status(response.getStatus()).build();
    }
}