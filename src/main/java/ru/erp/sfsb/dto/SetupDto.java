package ru.erp.sfsb.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.erp.sfsb.deserializer.SetupDeserializer;

import java.time.Duration;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = SetupDeserializer.class)
public class SetupDto extends AbstractDto {

    private Integer setupNumber;
    private String setupName;
    private Duration setupTime;
    private Duration processTime;
    private Duration interoperativeTime;
    private List<CutterToolDto> cutterToolDtoList;
    private List<MeasureToolDto> measureToolDtoList;
    private List<AdditionalToolDto> additionalToolDtoList;
    private List<SpecialToolDto> specialToolDtoList;
    private List<ToolingDto> toolingDtoList;
    private ProductionUnitDto productionUnitDto;
}