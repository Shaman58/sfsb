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
public class SpecialToolItemDto extends AbstractDto {

    @NotNull(message = "Название специнструмента не может быть пустым")
    private SpecialToolDto tool;
    private Integer amount;
    @JsonBackReference
    private SetupDto setup;
}