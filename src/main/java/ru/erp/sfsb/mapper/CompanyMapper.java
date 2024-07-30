package ru.erp.sfsb.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.CompanyDto;
import ru.erp.sfsb.model.Company;

@Component
public class CompanyMapper extends AbstractMapper<Company, CompanyDto> {

    public CompanyMapper(ModelMapper mapper) {
        super(mapper, Company.class, CompanyDto.class);
    }
}