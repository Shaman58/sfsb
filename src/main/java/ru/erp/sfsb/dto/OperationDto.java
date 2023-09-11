package ru.erp.sfsb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.erp.sfsb.model.OperationTimeManagement;

import javax.money.MonetaryAmount;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto extends AbstractDto {

    @NotBlank(message = "Название операции не может быть пустым")
    private String operationName;
    private MonetaryAmount paymentPerHour;
    private OperationTimeManagement operationTimeManagement;
}