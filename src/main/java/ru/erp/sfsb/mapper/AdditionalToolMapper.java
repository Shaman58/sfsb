package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.AdditionalToolDto;
import ru.erp.sfsb.dto.WorkpieceDto;
import ru.erp.sfsb.model.AdditionalTool;
import ru.erp.sfsb.model.Workpiece;

@Component
public class AdditionalToolMapper extends AbstractMapper<AdditionalTool, AdditionalToolDto> {

    private final ModelMapper mapper;

    public AdditionalToolMapper(ModelMapper mapper) {
        super(AdditionalTool.class, AdditionalToolDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(AdditionalTool.class, AdditionalToolDto.class)
                .addMappings(m -> m.skip(AdditionalToolDto::setWorkpieceDto))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(AdditionalToolDto.class, AdditionalTool.class)
                .addMappings(m -> m.skip(AdditionalTool::setWorkpiece))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(AdditionalTool source, AdditionalToolDto destination) {
        destination.setWorkpieceDto(mapper.map(source.getWorkpiece(), WorkpieceDto.class));
    }

    @Override
    protected void mapSpecificFields(AdditionalToolDto source, AdditionalTool destination) {
        destination.setWorkpiece(mapper.map(source.getWorkpieceDto(), Workpiece.class));
    }
}