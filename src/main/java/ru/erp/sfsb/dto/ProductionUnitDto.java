package ru.erp.sfsb.dto;

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

    private String unitName;
    private MonetaryAmount price;
    private ProductionAreaDto productionArea;
}