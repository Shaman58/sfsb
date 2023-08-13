package ru.erp.sfsb.dto;

import jakarta.validation.constraints.NotBlank;
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
public class ProductionAreaDto extends AbstractDto {

    @NotBlank(message = "Название не может быть пустым")
    private String areaName;
    private List<StoreDto> stores;
    @NotNull(message = "Компания не может быть пустой")
    private CompanyDto company;
}