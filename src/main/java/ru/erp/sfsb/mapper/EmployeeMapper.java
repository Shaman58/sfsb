package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.DepartmentDto;
import ru.erp.sfsb.dto.EmployeeDto;
import ru.erp.sfsb.model.Department;
import ru.erp.sfsb.model.Employee;

@Component
public class EmployeeMapper extends AbstractMapper<Employee, EmployeeDto> {

    private final ModelMapper mapper;

    public EmployeeMapper(ModelMapper mapper) {
        super(Employee.class, EmployeeDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(Employee.class, EmployeeDto.class)
                .addMappings(m -> m.skip(EmployeeDto::setDepartmentDto))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(EmployeeDto.class, Employee.class)
                .addMappings(m -> m.skip(Employee::setDepartment))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Employee source, EmployeeDto destination) {
        destination.setDepartmentDto(mapper.map(source.getDepartment(), DepartmentDto.class));
    }

    @Override
    protected void mapSpecificFields(EmployeeDto source, Employee destination) {
        destination.setDepartment(mapper.map(source.getDepartmentDto(), Department.class));
    }
}