package ru.erp.sfsb.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.erp.sfsb.deserializer.StoreDeserializer;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = StoreDeserializer.class)
public class StoreDto extends AbstractDto {

    private String storeName;
    private Map<WorkpieceDto, Integer> workpieces;
    private Map<CutterToolDto, Integer> cutterTools;
    private Map<MeasureToolDto, Integer> measureTools;
    private Map<ToolingDto, Integer> toolings;
    private Map<SpecialToolDto, Integer> specialTools;
}