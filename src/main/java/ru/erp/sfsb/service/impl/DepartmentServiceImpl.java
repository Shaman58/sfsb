package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.DepartmentDto;
import ru.erp.sfsb.mapper.DepartmentMapper;
import ru.erp.sfsb.model.Department;
import ru.erp.sfsb.repository.DepartmentRepository;
import ru.erp.sfsb.service.DepartmentService;
import ru.erp.sfsb.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class DepartmentServiceImpl extends AbstractService<DepartmentDto, Department, DepartmentRepository, DepartmentMapper>
        implements DepartmentService {

    private final DepartmentMapper mapper;
    private final DepartmentRepository repository;
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(DepartmentMapper mapper, DepartmentRepository repository, @Lazy EmployeeService employeeService) {
        super(mapper, repository, "Department");
        this.mapper = mapper;
        this.repository = repository;
        this.employeeService = employeeService;
    }

    @Override
    @Transactional
    public List<DepartmentDto> getAll() {
        log.info("Looking all Departments in DB");
        var departmentsDto = repository.findAll().stream().map(mapper::toDto).toList();
        departmentsDto.forEach(d -> d.setEmployeeDtoList(employeeService.getDepartmentEmployees(d.getId())));
        departmentsDto.forEach(d -> d.getEmployeeDtoList().forEach(e -> e.setDepartmentDto(null)));
        return departmentsDto;
    }

    @Override
    @Transactional
    public DepartmentDto save(DepartmentDto departmentDto) {
        log.info("Saving Department into DB");
        if (departmentDto.getEmployeeDtoList() != null) {
            departmentDto.setEmployeeDtoList(departmentDto.getEmployeeDtoList().stream().map(e -> employeeService.get(e.getId())).collect(toList()));
        } else {
            departmentDto.setEmployeeDtoList(new ArrayList<>());
        }
        return mapper.toDto(repository.save(mapper.toEntity(departmentDto)));
    }
}