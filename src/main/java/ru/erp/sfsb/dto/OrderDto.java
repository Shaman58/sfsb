package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends AbstractDto {

    @JsonIgnore
    private List<ItemDto> items;
    private String description;
    private String businessProposal;
    @NotNull(message = "Заказчик не может быть пустым")
    private CustomerDto customer;
    @NotNull(message = "Номер не может быть пустым")
    private Integer applicationNumber;
    @NotNull(message = "Менеджер не может быть пустым")
    private EmployeeDto employee;
    private ContactDto contact;
}