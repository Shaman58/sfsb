package ru.erp.sfsb.service;

import ru.erp.sfsb.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService extends Service<EmployeeDto> {
    List<EmployeeDto> getDepartmentEmployees(Long departmentId);
}