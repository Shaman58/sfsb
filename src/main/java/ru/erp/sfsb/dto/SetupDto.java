package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<AdditionalToolDto> additionalTools;
    @JsonManagedReference
    private List<SpecialToolItemDto> specialToolItems;
    @JsonManagedReference
    private List<CutterToolItemDto> cutterToolItems;
    private List<ToolingDto> toolings;
    @JsonBackReference
    private TechnologyDto technology;
    private boolean isGroup;
    private Integer perTime;
    private boolean isCooperate;
}