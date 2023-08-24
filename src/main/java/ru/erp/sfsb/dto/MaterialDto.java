package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.erp.sfsb.model.Geometry;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDto extends AbstractDto {

    @NotBlank(message = "Название материала не может быть пустым")
    private String materialName;
    @NotBlank(message = "ГОСТ материала не может быть пустым")
    private String gost;
    @NotNull(message = "Геометрия материала не может быть пустым")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Geometry geometry;
}