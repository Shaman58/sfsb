package ru.erp.sfsb.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDto extends AbstractDto {

    @NotBlank(message = "Название материала не может быть пустым")
    private String materialName;
    @Min(1)
    @Max(22000)
    @NotNull(message = " Велтчина плотности не может быть пустой")
    private Integer density;
}