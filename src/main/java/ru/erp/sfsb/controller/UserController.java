package ru.erp.sfsb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "API взаимодействия с UserService")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть всех пользователей")
    @GetMapping("/user")
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть пользователя по ID")
    @GetMapping("/user/{uuid}")
    public UserDto getUserByUuid(@PathVariable String uuid) {
        return userService.get(uuid);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Просмотреть все роли")
    @GetMapping("/role")
    public List<String> getAllRoles() {
        return userService.getRoles();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавить нового пользователя")
    @PostMapping("/user")
    public UserDto save(@RequestBody @Valid UserDto user) {
        return userService.save(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Изменить сведения о пользователе по ID")
    @PutMapping("/user/{uuid}")
    public UserDto update(@PathVariable String uuid, @RequestBody @Valid UserDto user) {
        return userService.update(uuid, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить пользователя по ID")
    @DeleteMapping("/user/{uuid}")
    public void delete(@PathVariable String uuid) {
        userService.delete(uuid);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавить аватар пользователю по ID")
    @PostMapping("/user/{uuid}")
    public void savePictureToUser(@PathVariable String uuid, MultipartFile file) {
        userService.setPicture(uuid, file);
    }
}