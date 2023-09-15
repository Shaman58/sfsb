package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalToolDto extends AbstractDto {

    @NotBlank(message = "Название приспособления не может быть пустым")
    private String toolName;
    @NotNull(message = "Заготовка не может быть пустой")
    private WorkpieceDto workpiece;
    @JsonBackReference
    private SetupDto setup;
    @Positive(message = "Количество должно быть больше 0")
    private Integer amount;
}