package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyDto extends AbstractDto {

    @NotBlank(message = "Номер чертежа не может быть пустым")
    private String drawingNumber;
    @NotBlank(message = "Название чертежа не может быть пустым")
    private String drawingName;
    private EmployeeDto employee;
    private Integer quantityOfDefectiveParts;
    private Integer quantityOfSetUpParts;
    private Integer quantityOfPartsFromWorkpiece;
    private WorkpieceDto workpiece;
    private boolean isComputed;
    @JsonIgnore
    private List<SetupDto> setups;
}