package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.EmployeeDto;
import ru.erp.sfsb.mapper.EmployeeMapper;
import ru.erp.sfsb.model.Employee;
import ru.erp.sfsb.repository.EmployeeRepository;
import ru.erp.sfsb.service.DepartmentService;
import ru.erp.sfsb.service.EmployeeService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class EmployeeServiceImpl extends AbstractService<EmployeeDto, Employee, EmployeeRepository, EmployeeMapper>
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
    public List<EmployeeDto> getDepartmentEmployees(Long departmentId) {
        log.info("Looking all employees with department id = {} in DB", departmentId);
        return repository.findEmployeesByDepartmentId(departmentId).stream().map(mapper::toDto).collect(toList());
    }

    @Override
    @Transactional
    public EmployeeDto save(EmployeeDto employeeDto) {
        log.info("Saving Employee into DB");
        if (employeeDto.getDepartment() != null) {
            employeeDto.setDepartment(departmentService.get(employeeDto.getDepartment().getId()));
        }
        return mapper.toDto(repository.save(mapper.toEntity(employeeDto)));
    }
}