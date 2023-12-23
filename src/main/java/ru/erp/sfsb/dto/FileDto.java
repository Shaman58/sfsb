package ru.erp.sfsb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FileDto extends AbstractDto {

    @NotBlank(message = "Отсутствует имя файла")
    private String filename;
    private String link;
    private UserDto user;
}