package ru.erp.sfsb.service;

import org.springframework.data.domain.Pageable;
import ru.erp.sfsb.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService extends Service<DepartmentDto> {

    List<DepartmentDto> getCompanyDepartments(Long companyId, Pageable pageable);
}