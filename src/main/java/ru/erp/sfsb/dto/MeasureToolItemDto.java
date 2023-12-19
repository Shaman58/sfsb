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
public class MeasureToolItemDto extends ToolItemDto {

    @NotNull(message = "Название мерителя не может быть пустым")
    private MeasureToolDto tool;

    @Override
    public MeasureToolDto getTool() {
        return this.tool;
    }
}