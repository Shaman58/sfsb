package ru.erp.sfsb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class ToolDto extends AbstractDto {

    @NotBlank(message = "Название специнструмента не может быть пустым")
    private String toolName;
    private String description;
}