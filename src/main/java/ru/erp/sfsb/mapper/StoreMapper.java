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
                .addMappings(m -> m.skip(StoreDto::setWorkpieceDtoIntegerMap))
                .addMappings(m -> m.skip(StoreDto::setCutterToolDtoIntegerMap))
                .addMappings(m -> m.skip(StoreDto::setMeasureToolDtoIntegerMap))
                .addMappings(m -> m.skip(StoreDto::setToolingDtoIntegerMap))
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
        destination.setCutterToolDtoIntegerMap(source.getCuttingTools().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), CutterToolDto.class),
                Map.Entry::getValue
        )));
        destination.setWorkpieceDtoIntegerMap(source.getWorkpieces().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), WorkpieceDto.class),
                Map.Entry::getValue
        )));
        destination.setMeasureToolDtoIntegerMap(source.getMeasuringTools().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), MeasureToolDto.class),
                Map.Entry::getValue
        )));
        destination.setToolingDtoIntegerMap(source.getToolings().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), ToolingDto.class),
                Map.Entry::getValue
        )));
        destination.setSpecialToolDtoIntegerMap(source.getSpecialTools().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), SpecialToolDto.class),
                Map.Entry::getValue
        )));
    }

    @Override
    protected void mapSpecificFields(StoreDto source, Store destination) {
        destination.setCuttingTools(source.getCutterToolDtoIntegerMap().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), CutterTool.class),
                Map.Entry::getValue
        )));
        destination.setWorkpieces(source.getWorkpieceDtoIntegerMap().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), Workpiece.class),
                Map.Entry::getValue
        )));
        destination.setMeasuringTools(source.getMeasureToolDtoIntegerMap().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), MeasureTool.class),
                Map.Entry::getValue
        )));
        destination.setToolings(source.getToolingDtoIntegerMap().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), Tooling.class),
                Map.Entry::getValue
        )));
        destination.setSpecialTools(source.getSpecialToolDtoIntegerMap().entrySet().stream().collect(Collectors.toMap(
                entry -> mapper.map(entry.getKey(), SpecialTool.class),
                Map.Entry::getValue
        )));
    }
}