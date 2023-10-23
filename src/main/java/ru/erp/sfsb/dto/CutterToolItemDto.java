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
public class CutterToolItemDto extends ToolItemDto {

    @NotNull(message = "Название инструмента не может быть пустым")
    private CutterToolDto tool;

    @Override
    public CutterToolDto getTool() {
        return this.tool;
    }
}