package ru.erp.sfsb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SpecialToolDto extends AbstractDto {

    @NotBlank(message = "Название специнструмента не может быть пустым")
    private String toolName;
    @NotNull(message = "Заготовка не может быть пустой")
    private WorkpieceDto workpiece;
    private Duration processTime;
}