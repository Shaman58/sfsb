package ru.erp.sfsb.dto;

import lombok.*;

import javax.money.MonetaryAmount;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDto extends AbstractDto {

    private String materialName;
    private Integer density;
    private MonetaryAmount price;
}