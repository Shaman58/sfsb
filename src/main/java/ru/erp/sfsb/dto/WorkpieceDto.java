package ru.erp.sfsb.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.money.MonetaryAmount;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkpieceDto extends AbstractDto {

    @NotNull(message = "Материал не может быть пустым")
    private MaterialDto material;
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer geom1;
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer geom2;
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer geom3;
    private MonetaryAmount price;
}