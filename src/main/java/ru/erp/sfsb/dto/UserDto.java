package ru.erp.sfsb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private String id;
    @NotBlank(message = "Никнэйм не может быть пустым")
    private String username;
    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;
    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;
    private String email;
    private String password;
    @NotNull()
    private List<String> roles;
    private String picture;
}