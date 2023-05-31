package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.*;
import ru.erp.sfsb.model.*;

import static java.util.stream.Collectors.toList;

@Component
public class CompanyMapper extends AbstractMapper<Company, CompanyDto> {

    private final ModelMapper mapper;

    public CompanyMapper(ModelMapper mapper) {
        super(Company.class, CompanyDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(Company.class, CompanyDto.class)
                .addMappings(m -> m.skip(CompanyDto::setDepartmentDtos))
                .addMappings(m -> m.skip(CompanyDto::setDirectorDto))
                .addMappings(m -> m.skip(CompanyDto::setOrderDtos))
                .addMappings(m -> m.skip(CompanyDto::setProductionAreaDtos))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(CompanyDto.class, Company.class)
                .addMappings(m -> m.skip(Company::setDepartments))
                .addMappings(m -> m.skip(Company::setDirector))
                .addMappings(m -> m.skip(Company::setOrders))
                .addMappings(m -> m.skip(Company::setProductionAreas))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Company source, CompanyDto destination) {
        destination.setProductionAreaDtos(source.getProductionAreas().stream()
                .map(e -> mapper.map(e, ProductionAreaDto.class)).collect(toList()));
        destination.setOrderDtos(source.getOrders().stream()
                .map(e -> mapper.map(e, OrderDto.class)).collect(toList()));
        destination.setDepartmentDtos(source.getDepartments().stream()
                .map(e -> mapper.map(e, DepartmentDto.class)).collect(toList()));
        destination.setDirectorDto(mapper.map(source.getDirector(), EmployeeDto.class));
    }

    @Override
    protected void mapSpecificFields(CompanyDto source, Company destination) {
        destination.setProductionAreas(source.getProductionAreaDtos().stream()
                .map(e -> mapper.map(e, ProductionArea.class)).collect(toList()));
        destination.setOrders(source.getOrderDtos().stream()
                .map(e -> mapper.map(e, Order.class)).collect(toList()));
        destination.setDepartments(source.getDepartmentDtos().stream()
                .map(e -> mapper.map(e, Department.class)).collect(toList()));
        destination.setDirector(mapper.map(source.getDirectorDto(), Employee.class));
    }
}