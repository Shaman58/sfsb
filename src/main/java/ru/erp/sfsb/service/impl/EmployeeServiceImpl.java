package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.erp.sfsb.dto.EmployeeDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.mapper.EmployeeMapper;
import ru.erp.sfsb.model.Employee;
import ru.erp.sfsb.repository.EmployeeRepository;
import ru.erp.sfsb.service.DepartmentService;
import ru.erp.sfsb.service.EmployeeService;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class EmployeeServiceImpl extends AbstractService<EmployeeDto, Employee, EmployeeRepository, EmployeeMapper>
        implements EmployeeService {
    private final EmployeeMapper mapper;
    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeMapper mapper, EmployeeRepository repository) {
        super(mapper, repository, "Employee");
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<EmployeeDto> getDepartmentEmployees(Long departmentId) {
        log.info("Looking all employees with department id = {} in DB", departmentId);
        return repository.findEmployeesByDepartmentId(departmentId).stream().map(mapper::toDto).collect(toList());
    }
}