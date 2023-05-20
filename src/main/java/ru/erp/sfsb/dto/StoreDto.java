package ru.erp.sfsb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto extends AbstractDto {

    private String storeName;
    private Map<WorkpieceDto, Integer> workpiecesDto;
    private Map<CutterToolDto, Integer> cuttingToolsDto;
    private Map<MeasureToolDto, Integer> measuringToolsDto;
}