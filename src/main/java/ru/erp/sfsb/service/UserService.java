package ru.erp.sfsb.service;


import org.springframework.web.multipart.MultipartFile;
import ru.erp.sfsb.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserDto save(UserDto user);

    UserDto update(String uuid, UserDto user);

    List<UserDto> getAll();

    UserDto get(String uuid);

    UserDto getOrNullObject(String uuid);

    List<String> getRoles();

    void delete(String uuid);

    void setAttribute(String uuid, Map<String, List<String>> attributes);

    Map<String, List<String>> getAttributes(String uuid);

    void setPicture(String uuid, MultipartFile file);
}