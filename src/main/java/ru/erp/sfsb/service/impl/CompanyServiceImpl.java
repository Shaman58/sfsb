package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.CompanyDto;
import ru.erp.sfsb.mapper.CompanyMapper;
import ru.erp.sfsb.model.Company;
import ru.erp.sfsb.repository.CompanyRepository;
import ru.erp.sfsb.service.CompanyService;
import ru.erp.sfsb.service.EmployeeService;

@Service
@Slf4j
public class CompanyServiceImpl extends AbstractService<CompanyDto, Company, CompanyRepository, CompanyMapper>
        implements CompanyService {

    private final EmployeeService employeeService;

    public CompanyServiceImpl(CompanyMapper mapper, CompanyRepository repository, EmployeeService employeeService) {
        super(mapper, repository, "Company");
        this.employeeService = employeeService;
    }

    @Override
    @Transactional
    public CompanyDto save(CompanyDto companyDto) {
        log.info("Saving Company into DB");
        if (companyDto.getDirector() != null) {
            var director = employeeService.get(companyDto.getDirector().getId());
            director.setDepartment(null);
            employeeService.update(director);
        }
        return mapper.toDto(repository.save(mapper.toEntity(companyDto)));
    }

    @Override
    @Transactional
    public CompanyDto update(CompanyDto companyDto) {
        log.info("Saving Company into DB");
        if (companyDto.getDirector() != null) {
            get(companyDto.getId());
            var director = employeeService.get(companyDto.getDirector().getId());
            director.setDepartment(null);
            employeeService.update(director);
        }
        return mapper.toDto(repository.save(mapper.toEntity(companyDto)));
    }
}