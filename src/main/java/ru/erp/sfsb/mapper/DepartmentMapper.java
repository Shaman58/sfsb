package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.DepartmentDto;
import ru.erp.sfsb.model.Department;

@Component
public class DepartmentMapper extends AbstractMapper<Department, DepartmentDto> {

    public DepartmentMapper() {
        super(Department.class, DepartmentDto.class);
    }
}