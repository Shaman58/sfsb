package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.CompanyDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.mapper.CompanyMapper;
import ru.erp.sfsb.model.Company;
import ru.erp.sfsb.repository.CompanyRepository;
import ru.erp.sfsb.service.CompanyService;
import ru.erp.sfsb.service.EmployeeService;

import static java.lang.String.format;

@Service
@Slf4j
public class CompanyServiceImpl extends AbstractService<CompanyDto, Company, CompanyRepository, CompanyMapper>
        implements CompanyService {
    private final CompanyMapper mapper;
    private final CompanyRepository repository;
    private final EmployeeService employeeService;

    public CompanyServiceImpl(CompanyMapper mapper, CompanyRepository repository, EmployeeService employeeService) {
        super(mapper, repository, "Company");
        this.mapper = mapper;
        this.repository = repository;
        this.employeeService = employeeService;
    }

    @Override
    @Transactional
    public CompanyDto get(Long id) {
        log.info("Looking Company with id={} in DB", id);
        return mapper.toDto((repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("There is no Company with id=%d in database", id)))));
    }

    @Override
    @Transactional
    public CompanyDto save(CompanyDto companyDto) {
        log.info("Saving Company into DB");
        companyDto.setId(1L);
        if (companyDto.getDirector() != null && companyDto.getDirector().getId() != null) {
            companyDto.setDirector(employeeService.get(companyDto.getDirector().getId()));
        }
        return mapper.toDto(repository.save(mapper.toEntity(companyDto)));
    }
}