package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.UserDto;
import ru.erp.sfsb.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseBody
    @GetMapping("/user")
    public List<UserDto> getAllUsers() {
        return userService.getUsers();
    }

    @ResponseBody
    @GetMapping("/role")
    public List<String> getAllRoles() {
        return userService.getRoles();
    }

    @ResponseBody
    @PostMapping("/user")
    public ResponseEntity save(@RequestBody UserDto user) {
        var response = userService.createKCUser(user);
        return ResponseEntity.status(response.getStatus()).build();
    }
}