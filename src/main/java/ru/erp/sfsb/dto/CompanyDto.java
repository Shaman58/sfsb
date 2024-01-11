package ru.erp.sfsb.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyDto extends OrganizationDto {

    private FileDto logo;
}