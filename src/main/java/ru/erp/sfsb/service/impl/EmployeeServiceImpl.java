package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.EmployeeDto;
import ru.erp.sfsb.mapper.EmployeeMapper;
import ru.erp.sfsb.model.Employee;
import ru.erp.sfsb.repository.EmployeeRepository;
import ru.erp.sfsb.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl extends AbstractService<EmployeeDto, Employee, EmployeeRepository, EmployeeMapper>
        implements EmployeeService {

    public EmployeeServiceImpl(EmployeeMapper mapper, EmployeeRepository repository) {
        super(mapper, repository, "Employee");
        this.mapper = mapper;
        this.repository = repository;
    }
}