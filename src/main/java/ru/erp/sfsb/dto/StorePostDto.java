package ru.erp.sfsb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorePostDto {

    private Long id;
    private String storeName;
    private Map<Integer, Integer> workpieces;
    private Map<Integer, Integer> cuttingTools;
    private Map<Integer, Integer> measuringTools;
    private Map<Integer, Integer> toolings;
    private Map<Integer, Integer> specialTools;
}