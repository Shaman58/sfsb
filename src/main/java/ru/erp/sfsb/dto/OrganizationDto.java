package ru.erp.sfsb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class OrganizationDto extends AbstractDto {

    @NotBlank(message = "Название компании не может быть пустым")
    private String companyName;
    @NotBlank(message = "Адрес не может быть пустым")
    private String address;
    @Pattern(regexp = "^[0-9]{10}$")
    private String inn;
    @Pattern(regexp = "^[0-9]{9}$")
    private String kpp;
    @Pattern(regexp = "^[0-9]{13}$")
    private String ogrn;
    @Pattern(regexp = "^[0-9]{20}$")
    private String paymentAccount;
    @NotBlank(message = "Банк не может быть пустым")
    private String bank;
    @Pattern(regexp = "^[0-9]{9}$")
    private String bik;
    @Pattern(regexp = "^[0-9]{20}$")
    private String correspondentAccount;
    @Pattern(regexp = "^\\+?[78][-(]?\\d{3}\\)?-?\\d{3}-?\\d{2}-?\\d{2}$")
    private String phoneNumber;
    @Pattern(regexp = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")
    private String email;
}