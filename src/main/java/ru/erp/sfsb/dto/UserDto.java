package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = "Никнэйм не может быть пустым")
    private String username;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull()
    private List<String> roles;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String picture;
}