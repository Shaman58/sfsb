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
    @ResponseBody
    @GetMapping("/user")
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/role")
    public List<String> getAllRoles() {
        return userService.getRoles();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/user")
    public UserDto save(@RequestBody @Valid UserDto user) {
        return userService.save(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PutMapping("/user/{uuid}")
    public UserDto update(@PathVariable String uuid, @RequestBody @Valid UserDto user) {
        log.debug(user.toString());
        return userService.update(uuid, user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PutMapping("/user/file/{uuid}")
    public void setPicture(@PathVariable String uuid, MultipartFile file) {
        userService.setPicture(uuid, file);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @DeleteMapping("/user/{uuid}")
    public void delete(@PathVariable String uuid) {
        userService.delete(uuid);
    }
}