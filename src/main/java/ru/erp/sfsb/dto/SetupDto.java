package ru.erp.sfsb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
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