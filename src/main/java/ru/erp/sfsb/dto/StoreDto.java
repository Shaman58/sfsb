package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Map<WorkpieceDto, Integer> workpieces;
    private Map<CutterToolDto, Integer> cutterTools;
    private Map<MeasureToolDto, Integer> measureTools;
    private Map<ToolingDto, Integer> toolings;
    private Map<SpecialToolDto, Integer> specialTools;
    @JsonIgnore
    private ProductionAreaDto productionArea;
}