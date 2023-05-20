package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.CutterToolDto;
import ru.erp.sfsb.dto.MeasureToolDto;
import ru.erp.sfsb.dto.StoreDto;
import ru.erp.sfsb.dto.WorkpieceDto;
import ru.erp.sfsb.model.CutterTool;
import ru.erp.sfsb.model.MeasureTool;
import ru.erp.sfsb.model.Store;
import ru.erp.sfsb.model.Workpiece;

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
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(StoreDto.class, Store.class)
                .addMappings(m -> m.skip(Store::setWorkpieces))
                .addMappings(m -> m.skip(Store::setCuttingTools))
                .addMappings(m -> m.skip(Store::setMeasuringTools))
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
    }
}