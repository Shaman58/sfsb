package ru.erp.sfsb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.money.MonetaryAmount;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolingDto extends AbstractDto {

    @NotBlank(message = "Название оснастки не может быть пустым")
    private String toolName;
    private String description;
    private MonetaryAmount price;
    private Integer amount;
}