package ru.erp.sfsb.dto;

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

    private String drawingNumber;
    private String drawingName;
    private EmployeeDto employee;
    private List<SetupDto> setups;
    private Integer quantityOfDefectiveParts;
    private Integer quantityOfSetUpParts;
    private Integer quantityOfPartsFromWorkpiece;
    private WorkpieceDto workpiece;
}