package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.CompanyDto;
import ru.erp.sfsb.mapper.CompanyMapper;
import ru.erp.sfsb.model.Company;
import ru.erp.sfsb.repository.CompanyRepository;
import ru.erp.sfsb.service.*;

import static java.util.stream.Collectors.toList;

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
    public CompanyDto save(CompanyDto companyDto) {
        log.info("Saving Company into DB");
        companyDto.setDirectorDto(employeeService.get(companyDto.getDirectorDto().getId()));
        companyDto.setDepartmentDtos(companyDto.getDepartmentDtos().stream().map(e -> departmentService.get(e.getId())).collect(toList()));
        companyDto.setProductionAreaDtos(companyDto.getProductionAreaDtos().stream().map(e -> productionAreaService.get(e.getId())).collect(toList()));
        companyDto.setOrderDtos(companyDto.getOrderDtos().stream().map(e -> orderService.get(e.getId())).collect(toList()));
        return mapper.toDto(repository.save(mapper.toEntity(companyDto)));
    }
}