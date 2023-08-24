package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ProductionUnitDto extends AbstractDto {

    @NotBlank(message = "Название не может быть пустым")
    private String unitName;
    private Integer unitNumber;
    @JsonIgnore
    private ProductionAreaDto productionArea;
    private MonetaryAmount price;
    private MonetaryAmount paymentPerHour;
}