package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    private List<ItemDto> items;
    private String description;
    private String businessProposal;
    @NotNull(message = "Заказчик не может быть пустым")
    private CustomerDto customer;
    @NotNull(message = "Номер не может быть пустым")
    private Integer applicationNumber;
    private UserDto user;
    private List<FileDto> files;
}