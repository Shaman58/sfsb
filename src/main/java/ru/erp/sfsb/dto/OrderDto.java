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
public class OrderDto extends AbstractDto {

    private List<ItemDto> itemDtos;
    private String description;
    private String businessProposal;
    private String recipient;
    private Integer applicationNumber;
}