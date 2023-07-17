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
        return repository.findEmployeesByDepartmentId(departmentId).stream().map(mapper::toDto).map(this::dropCompany).collect(toList());
    }

    @Override
    @Transactional
    public List<EmployeeDto> getAll(Pageable pageable) {
        log.info("Looking all Employees in DB");
        var entities = repository.findAll(pageable);
        return entities.stream().map(mapper::toDto).map(this::dropCompany).collect(toList());
    }

    @Override
    @Transactional
    public EmployeeDto get(@PathVariable Long id) {
        log.info("Looking Employee with id={} in DB", id);
        return dropCompany(mapper.toDto((repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("There is no Employee with id=%d in database", id))))));
    }

    @Override
    @Transactional
    public EmployeeDto save(EmployeeDto employeeDto) {
        log.info("Saving Employee into DB");
//        if (employeeDto.getDepartment() != null) {
//            employeeDto.setDepartment(departmentService.get(employeeDto.getDepartment().getId()));
//        }
        var savedEmployee = mapper.toDto(repository.save(mapper.toEntity(employeeDto)));
        return dropCompany(savedEmployee);
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeDto employeeDto) {
        log.info("Saving Employee into DB");
        get(employeeDto.getId());
        return dropCompany(mapper.toDto(repository.save(mapper.toEntity(employeeDto))));
    }

    private EmployeeDto dropCompany(EmployeeDto employeeDto) {
//        if (employeeDto.getDepartment() != null) {
//            employeeDto.getDepartment().setCompany(null);
//        }
        return employeeDto;
    }
}