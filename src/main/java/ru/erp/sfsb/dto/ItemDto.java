package ru.erp.sfsb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto extends AbstractDto {

    private TechnologyDto technology;
    private boolean isCustomerMaterial;
    private Integer quantity;
    private Duration estimatedDuration;
    private Duration actualDuration;
    private boolean isAccepted;
}