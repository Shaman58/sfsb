package ru.erp.sfsb.service;


import jakarta.ws.rs.core.Response;
import ru.erp.sfsb.dto.UserDto;

import java.util.List;

public interface UserService {

    Response createKCUser(UserDto user);

    List<UserDto> getUsers();

    List<String> getRoles();

    void addRoles(String userId, List<String> roles);
}