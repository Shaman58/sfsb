package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.money.MonetaryAmount;
import java.time.Duration;
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
    private UserDto user;
    private Integer quantityOfDefectiveParts;
    private Integer quantityOfSetUpParts;
    private Integer quantityOfPartsFromWorkpiece;
    private WorkpieceDto workpiece;
    private Duration technologistTime;
    private boolean isComputed;
    private boolean isAssembly;
    @JsonManagedReference
    private List<SetupDto> setups;
    private MonetaryAmount outsourcedCosts;
    private String outsourcedCostsDescription;
    private String blocked;
}