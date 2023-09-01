package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.money.MonetaryAmount;
import java.time.Duration;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto extends AbstractDto {

    @JsonBackReference
    private OrderDto order;
    @Valid
    private TechnologyDto technology;
    @NotNull(message = "Материал заказчика не должно быть пустым")
    private boolean isCustomerMaterial;
    @NotNull(message = "Количество не должно быть пустым")
    private Integer quantity;
    private Duration estimatedDuration;
    private Duration actualDuration;
    private boolean isAccepted;
    private MonetaryAmount price;
}