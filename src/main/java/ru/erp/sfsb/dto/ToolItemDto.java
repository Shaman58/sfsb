package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.money.MonetaryAmount;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class ToolItemDto extends AbstractDto {

    private Integer amount;
    @JsonBackReference
    private SetupDto setup;
    private MonetaryAmount price;

    public abstract ToolDto getTool();
}