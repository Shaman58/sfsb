package ru.erp.sfsb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.money.MonetaryAmount;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MeasureToolDto extends AbstractDto {

    @NotBlank(message = "Название инструмента не может быть пустым")
    private String toolName;
    private String description;
    private MonetaryAmount price;
    private Integer amount;
}