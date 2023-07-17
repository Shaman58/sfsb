package ru.erp.sfsb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Duration;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalToolDto extends AbstractDto {

    @NotBlank(message = "Название приспособления не может быть пустым")
    private String toolName;
    @NotNull(message = "Заготовка не может быть пустой")
    private WorkpieceDto workpiece;
    private Duration processTime;
}