package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.erp.sfsb.dto.CompanyDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.mapper.CompanyMapper;
import ru.erp.sfsb.model.Company;
import ru.erp.sfsb.repository.CompanyRepository;
import ru.erp.sfsb.service.*;

import static java.lang.String.format;

@Service
@Slf4j
public class CompanyServiceImpl extends AbstractService<CompanyDto, Company, CompanyRepository, CompanyMapper>
        implements CompanyService {
    private final CompanyMapper mapper;
    private final CompanyRepository repository;
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final OrderService orderService;
    private final ProductionAreaService productionAreaService;

    public CompanyServiceImpl(CompanyMapper mapper, CompanyRepository repository, DepartmentService departmentService, EmployeeService employeeService, OrderService orderService, ProductionAreaService productionAreaService) {
        super(mapper, repository, "Company");
        this.mapper = mapper;
        this.repository = repository;
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.orderService = orderService;
        this.productionAreaService = productionAreaService;
    }

    @Override
    @Transactional
    public CompanyDto get(@PathVariable Long id) {
        log.info("Looking Company with id={} in DB", id);
        var companyDto = mapper.toDto((repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("There is no Company with id=%d in database", id)))));
        deleteLinks(companyDto);
        return companyDto;
    }

    @Override
    @Transactional
    public CompanyDto save(CompanyDto companyDto) {
        log.info("Saving Company into DB");
        companyDto.setId(1L);
        companyDto.setDepartments(departmentService.getAll());
        companyDto.setProductionAreas(productionAreaService.getAll());
        companyDto.setOrders(orderService.getAll());
        if (companyDto.getDirector() != null && companyDto.getDirector().getId() != null) {
            companyDto.setDirector(employeeService.get(companyDto.getDirector().getId()));
        }
        var savedCompanyDto = mapper.toDto(repository.save(mapper.toEntity(companyDto)));
        deleteLinks(savedCompanyDto);
        return savedCompanyDto;
    }

    private void deleteLinks(CompanyDto companyDto) {
        companyDto.setOrders(null);
        companyDto.setProductionAreas(null);
    }
}