package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.MaterialDto;
import ru.erp.sfsb.dto.WorkpieceDto;
import ru.erp.sfsb.model.Material;
import ru.erp.sfsb.model.Workpiece;

@Component
public class WorkpieceMapper extends AbstractMapper<Workpiece, WorkpieceDto> {

    private final ModelMapper mapper;

    public WorkpieceMapper(ModelMapper mapper) {
        super(Workpiece.class, WorkpieceDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(Workpiece.class, WorkpieceDto.class)
                .addMappings(m -> m.skip(WorkpieceDto::setMaterialDto))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(WorkpieceDto.class, Workpiece.class)
                .addMappings(m -> m.skip(Workpiece::setMaterial))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Workpiece source, WorkpieceDto destination) {
        destination.setMaterialDto(mapper.map(source.getMaterial(), MaterialDto.class));
    }

    @Override
    protected void mapSpecificFields(WorkpieceDto source, Workpiece destination) {
        destination.setMaterial(mapper.map(source.getMaterialDto(), Material.class));
    }
}