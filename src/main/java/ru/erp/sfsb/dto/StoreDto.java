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
    private Map<WorkpieceDto, Integer> workpieceDtoIntegerMap;
    private Map<CutterToolDto, Integer> cutterToolDtoIntegerMap;
    private Map<MeasureToolDto, Integer> measureToolDtoIntegerMap;
    private Map<ToolingDto, Integer> toolingDtoIntegerMap;
    private Map<SpecialToolDto, Integer> specialToolDtoIntegerMap;
}