package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.mapper.EmployeeMapper;
import ru.erp.sfsb.model.Employee;
import ru.erp.sfsb.repository.EmployeeRepository;
import ru.erp.sfsb.service.DepartmentService;
import ru.erp.sfsb.service.EmployeeService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class EmployeeServiceImpl extends AbstractService<ru.erp.sfsb.dto.EmployeeDto, Employee, EmployeeRepository, EmployeeMapper>
        implements EmployeeService {
    private final EmployeeMapper mapper;
    private final EmployeeRepository repository;
    private final DepartmentService departmentService;

    public EmployeeServiceImpl(EmployeeMapper mapper, EmployeeRepository repository, @Lazy DepartmentService departmentService) {
        super(mapper, repository, "Employee");
        this.mapper = mapper;
        this.repository = repository;
        this.departmentService = departmentService;
    }

    @Override
    @Transactional
    public List<ru.erp.sfsb.dto.EmployeeDto> getDepartmentEmployees(Long departmentId) {
        log.info("Looking all employees with department id = {} in DB", departmentId);
        return repository.findEmployeesByDepartmentId(departmentId).stream().map(mapper::toDto).collect(toList());
    }

    @Override
    @Transactional
    public ru.erp.sfsb.dto.EmployeeDto save(ru.erp.sfsb.dto.EmployeeDto employeeDto) {
        log.info("Saving Employee into DB");
        if (employeeDto.getDepartmentDto() != null) {
            employeeDto.setDepartmentDto(departmentService.get(employeeDto.getDepartmentDto().getId()));
        }
        return mapper.toDto(repository.save(mapper.toEntity(employeeDto)));
    }
}