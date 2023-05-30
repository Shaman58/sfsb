package ru.erp.sfsb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionAreaPostDto {

    private Long id;
    private String areaName;
    private StorePostDto storeDto;
}