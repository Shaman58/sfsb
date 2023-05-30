package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.EmployeeDto;
import ru.erp.sfsb.dto.SetupDto;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.dto.WorkpieceDto;
import ru.erp.sfsb.model.Employee;
import ru.erp.sfsb.model.Setup;
import ru.erp.sfsb.model.Technology;
import ru.erp.sfsb.model.Workpiece;

import static java.util.stream.Collectors.toList;

@Component
public class TechnologyMapper extends AbstractMapper<Technology, TechnologyDto> {

    private final ModelMapper mapper;

    @Autowired
    public TechnologyMapper(ModelMapper mapper) {
        super(Technology.class, TechnologyDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(Technology.class, TechnologyDto.class)
                .addMappings(m -> m.skip(TechnologyDto::setWorkpieceDto))
                .addMappings(m -> m.skip(TechnologyDto::setEmployeeDto))
                .addMappings(m -> m.skip(TechnologyDto::setSetups))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(TechnologyDto.class, Technology.class)
                .addMappings(m -> m.skip(Technology::setSetups))
                .addMappings(m -> m.skip(Technology::setWorkpiece))
                .addMappings(m -> m.skip(Technology::setEmployee))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Technology source, TechnologyDto destination) {
        destination.setEmployeeDto(mapper.map(source.getEmployee(), EmployeeDto.class));
        destination.setWorkpieceDto(mapper.map(source.getWorkpiece(), WorkpieceDto.class));
        destination.setSetups(source.getSetups().stream().map(e -> mapper.map(e, SetupDto.class)).collect(toList()));
    }

    @Override
    protected void mapSpecificFields(TechnologyDto source, Technology destination) {
        destination.setEmployee(mapper.map(source.getEmployeeDto(), Employee.class));
        destination.setWorkpiece(mapper.map(source.getWorkpieceDto(), Workpiece.class));
        destination.setSetups(source.getSetups().stream().map(e -> mapper.map(e, Setup.class)).collect(toList()));
    }
}