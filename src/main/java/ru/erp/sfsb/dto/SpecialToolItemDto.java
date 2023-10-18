package ru.erp.sfsb.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SpecialToolItemDto extends ToolItemDto {

    @NotNull(message = "Название специнструмента не может быть пустым")
    private SpecialToolDto tool;
}