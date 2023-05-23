package ru.erp.sfsb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionAreaPostDto {

    private String areaName;
    private List<Long> productionUnitsIds;
    private StorePostDto storePostDto;
}