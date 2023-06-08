package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.CompanyDto;
import ru.erp.sfsb.model.Company;

@Component
public class CompanyMapper extends AbstractMapper<Company, CompanyDto> {

    public CompanyMapper() {
        super(Company.class, CompanyDto.class);
    }
}