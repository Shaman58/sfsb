package ru.erp.sfsb.service;

import ru.erp.sfsb.dto.CompanyDto;

public interface CompanyService extends Service<CompanyDto> {

    CompanyDto getCompany();

    CompanyDto updateCompany(CompanyDto company);
}