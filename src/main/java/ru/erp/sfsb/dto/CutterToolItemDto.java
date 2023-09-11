package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CutterToolItemDto extends AbstractDto {

    @NotNull(message = "Название инструмента не может быть пустым")
    private CutterToolDto tool;
    private Integer amount;
    @JsonBackReference
    private SetupDto setup;
}