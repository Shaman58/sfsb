package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.erp.sfsb.model.MeasureTool;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MeasureToolItemDto extends AbstractDto {

    @NotNull(message = "Название мерителя не может быть пустым")
    private MeasureTool tool;
    private Integer amount;
    @JsonBackReference
    private SetupDto setup;
}