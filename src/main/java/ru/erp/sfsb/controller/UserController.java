package ru.erp.sfsb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.erp.sfsb.dto.UserDto;
import ru.erp.sfsb.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user")
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{uuid}")
    public UserDto getUserByUuid(@PathVariable String uuid) {
        return userService.get(uuid);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/role")
    public List<String> getAllRoles() {
        return userService.getRoles();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public UserDto save(@RequestBody @Valid UserDto user) {
        return userService.save(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user/{uuid}")
    public UserDto update(@PathVariable String uuid, @RequestBody @Valid UserDto user) {
        log.debug(user.toString());
        return userService.update(uuid, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/user/{uuid}")
    public void delete(@PathVariable String uuid) {
        userService.delete(uuid);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/{uuid}")
    public void savePictureToUser(@PathVariable String uuid, MultipartFile file) {
        userService.setPicture(uuid, file);
    }
}