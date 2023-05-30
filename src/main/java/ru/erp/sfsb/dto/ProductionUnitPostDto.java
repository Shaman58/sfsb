package ru.erp.sfsb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionUnitPostDto {

    private Long id;
    private String unitName;
    private BigDecimal priceAmount;
    private String priceCurrency;
    private Long productionAreaId;
}