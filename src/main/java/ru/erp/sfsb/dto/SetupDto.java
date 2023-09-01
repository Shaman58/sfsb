package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private OperationDto operation;
    private Duration setupTime;
    private Duration processTime;
    private Duration interoperativeTime;
    private List<MeasureToolDto> measureTools;
    private List<AdditionalToolDto> additionalTools;
    private List<SpecialToolDto> specialTools;
    private List<ToolingDto> toolings;
    private ProductionUnitDto productionUnit;
    @JsonBackReference
    private TechnologyDto technology;
    private boolean isGroup;
    private Integer perTime;
}