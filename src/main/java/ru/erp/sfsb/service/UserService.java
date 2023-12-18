package ru.erp.sfsb.service;


import org.springframework.web.multipart.MultipartFile;
import ru.erp.sfsb.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto save(UserDto user);

    UserDto update(String uuid, UserDto user);

    List<UserDto> getAll();

    UserDto get(String uuid);

    List<String> getRoles();

    void delete(String uuid);

    void setPicture(String uuid, MultipartFile file);
}