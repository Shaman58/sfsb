package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.*;
import ru.erp.sfsb.model.*;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StoreMapper extends AbstractMapper<Store, StoreDto> {
    private final ModelMapper mapper;

    public StoreMapper(ModelMapper mapper) {
        super(Store.class, StoreDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(Store.class, StoreDto.class)
                .addMappings(m -> m.skip(StoreDto::setWorkpiecesDto))
                .addMappings(m -> m.skip(StoreDto::setCuttingToolsDto))
                .addMappings(m -> m.skip(StoreDto::setMeasuringToolsDto))
                .addMappings(m -> m.skip(StoreDto::setToolingsDto))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(StoreDto.class, Store.class)
                .addMappings(m -> m.skip(Store::setWorkpieces))
                .addMappings(m -> m.skip(Store::setCuttingTools))
                .addMappings(m -> m.skip(Store::setMeasuringTools))
                .addMappings(m -> m.skip(Store::setToolings))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Store source, StoreDto destination) {
        destination.setCuttingToolsDto(source.getCuttingTools().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), CutterToolDto.class),
                Map.Entry::getValue
        )));
        destination.setWorkpiecesDto(source.getWorkpieces().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), WorkpieceDto.class),
                Map.Entry::getValue
        )));
        destination.setMeasuringToolsDto(source.getMeasuringTools().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), MeasureToolDto.class),
                Map.Entry::getValue
        )));
        destination.setToolingsDto(source.getToolings().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), ToolingDto.class),
                Map.Entry::getValue
        )));
    }

    @Override
    protected void mapSpecificFields(StoreDto source, Store destination) {
        destination.setCuttingTools(source.getCuttingToolsDto().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), CutterTool.class),
                Map.Entry::getValue
        )));
        destination.setWorkpieces(source.getWorkpiecesDto().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), Workpiece.class),
                Map.Entry::getValue
        )));
        destination.setMeasuringTools(source.getMeasuringToolsDto().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), MeasureTool.class),
                Map.Entry::getValue
        )));
        destination.setToolings(source.getToolingsDto().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), Tooling.class),
                Map.Entry::getValue
        )));
    }
}