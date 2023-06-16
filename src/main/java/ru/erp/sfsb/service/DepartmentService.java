package ru.erp.sfsb.service;

import ru.erp.sfsb.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService extends Service<DepartmentDto> {

    List<DepartmentDto> getCompanyDepartments(Long companyId);
}