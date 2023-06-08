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
public class CompanyDto extends AbstractDto {

    private String companyName;
    private String address;
    private String inn;
    private String kpp;
    private String okpo;
    private String paymentAccount;
    private String bank;
    private String bik;
    private String correspondentAccount;
    private String phoneNumber;
    private List<DepartmentDto> departments;
    private EmployeeDto director;
    private String email;
    private List<OrderDto> orders;
    private List<ProductionAreaDto> productionAreas;
}