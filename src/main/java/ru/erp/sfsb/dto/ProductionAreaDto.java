package ru.erp.sfsb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.erp.sfsb.model.Store;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductionAreaDto extends AbstractDto {

    private String areaName;
    private List<ProductionUnitDto> productionUnits;
    private StoreDto store;
}