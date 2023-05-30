package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.DepartmentDto;
import ru.erp.sfsb.model.Department;
import ru.erp.sfsb.model.Employee;

@Component
public class EmployeeMapper extends AbstractMapper<Employee, ru.erp.sfsb.dto.EmployeeDto> {

    private final ModelMapper mapper;

    public EmployeeMapper(ModelMapper mapper) {
        super(Employee.class, ru.erp.sfsb.dto.EmployeeDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(Employee.class, ru.erp.sfsb.dto.EmployeeDto.class)
                .addMappings(m -> m.skip(ru.erp.sfsb.dto.EmployeeDto::setDepartmentDto))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(ru.erp.sfsb.dto.EmployeeDto.class, Employee.class)
                .addMappings(m -> m.skip(Employee::setDepartment))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Employee source, ru.erp.sfsb.dto.EmployeeDto destination) {
        destination.setDepartmentDto(mapper.map(source.getDepartment(), DepartmentDto.class));
    }

    @Override
    protected void mapSpecificFields(ru.erp.sfsb.dto.EmployeeDto source, Employee destination) {
        destination.setDepartment(mapper.map(source.getDepartmentDto(), Department.class));
    }
}