package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
public class UserDto {

    private String id;

    @JsonInclude(NON_NULL)
    @NotBlank(message = "Никнэйм не может быть пустым")
    private String username;

    @JsonInclude(NON_NULL)
    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;

    @JsonInclude(NON_NULL)
    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;

    @JsonInclude(NON_NULL)
    private String email;

    @JsonInclude(NON_NULL)
    private String password;

    @JsonInclude(NON_NULL)
    @NotNull()
    private List<String> roles;

    @JsonInclude(NON_NULL)
    private String picture;
}