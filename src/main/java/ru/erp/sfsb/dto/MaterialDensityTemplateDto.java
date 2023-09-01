package ru.erp.sfsb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDensityTemplateDto extends AbstractDto {

    @NotBlank(message = "Название материала не может быть пустым")
    private String materialTypeName;
    @NotBlank(message = "Плотность материала не может быть пустой")
    private Integer density;
}