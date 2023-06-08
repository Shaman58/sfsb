package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.EmployeeDto;
import ru.erp.sfsb.model.Employee;

@Component
public class EmployeeMapper extends AbstractMapper<Employee, EmployeeDto> {

    public EmployeeMapper() {
        super(Employee.class, EmployeeDto.class);
    }
}