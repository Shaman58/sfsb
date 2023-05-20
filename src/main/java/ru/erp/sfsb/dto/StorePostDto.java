package ru.erp.sfsb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorePostDto {

    private String storeName;
    private Map<Integer, Integer> workpiecesDto;
    private Map<Integer, Integer> cuttingToolsDto;
    private Map<Integer, Integer> measuringToolsDto;
}