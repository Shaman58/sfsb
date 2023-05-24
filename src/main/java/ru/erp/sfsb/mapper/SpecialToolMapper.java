package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.SpecialToolDto;
import ru.erp.sfsb.dto.WorkpieceDto;
import ru.erp.sfsb.model.SpecialTool;
import ru.erp.sfsb.model.Workpiece;

@Component
public class SpecialToolMapper extends AbstractMapper<SpecialTool, SpecialToolDto> {

    private final ModelMapper mapper;

    public SpecialToolMapper(ModelMapper mapper) {
        super(SpecialTool.class, SpecialToolDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(SpecialTool.class, SpecialToolDto.class)
                .addMappings(m -> m.skip(SpecialToolDto::setWorkpieceDto))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(SpecialToolDto.class, SpecialTool.class)
                .addMappings(m -> m.skip(SpecialTool::setWorkpiece))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(SpecialTool source, SpecialToolDto destination) {
        destination.setWorkpieceDto(mapper.map(source.getWorkpiece(), WorkpieceDto.class));
    }

    @Override
    protected void mapSpecificFields(SpecialToolDto source, SpecialTool destination) {
        destination.setWorkpiece(mapper.map(source.getWorkpieceDto(), Workpiece.class));
    }
}