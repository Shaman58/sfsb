package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.*;
import ru.erp.sfsb.model.*;

import static java.util.stream.Collectors.toList;

@Component
public class SetupMapper extends AbstractMapper<Setup, SetupDto> {

    private final ModelMapper mapper;

    @Autowired
    public SetupMapper(ModelMapper mapper) {
        super(Setup.class, SetupDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(Setup.class, SetupDto.class)
                .addMappings(m -> m.skip(SetupDto::setAdditionalToolDtoList))
                .addMappings(m -> m.skip(SetupDto::setSpecialToolDtoList))
                .addMappings(m -> m.skip(SetupDto::setCutterToolDtoList))
                .addMappings(m -> m.skip(SetupDto::setProductionUnitDto))
                .addMappings(m -> m.skip(SetupDto::setMeasureToolDtoList))
                .addMappings(m -> m.skip(SetupDto::setToolingDtoList))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(SetupDto.class, Setup.class)
                .addMappings(m -> m.skip(Setup::setAdditionalTools))
                .addMappings(m -> m.skip(Setup::setSpecialTools))
                .addMappings(m -> m.skip(Setup::setCutterTools))
                .addMappings(m -> m.skip(Setup::setProductionUnit))
                .addMappings(m -> m.skip(Setup::setMeasureTools))
                .addMappings(m -> m.skip(Setup::setToolings))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Setup source, SetupDto destination) {
        destination.setAdditionalToolDtoList(source.getAdditionalTools().stream().map(
                e -> mapper.map(e, AdditionalToolDto.class)
        ).collect(toList()));
        destination.setSpecialToolDtoList(source.getSpecialTools().stream().map(
                e -> mapper.map(e, SpecialToolDto.class)
        ).collect(toList()));
        destination.setCutterToolDtoList(source.getCutterTools().stream().map(
                e -> mapper.map(e, CutterToolDto.class)
        ).collect(toList()));
        destination.setProductionUnitDto(mapper.map(source.getProductionUnit(), ProductionUnitDto.class));
        destination.setMeasureToolDtoList(source.getMeasureTools().stream().map(
                e -> mapper.map(e, MeasureToolDto.class)
        ).collect(toList()));
        destination.setToolingDtoList(source.getToolings().stream().map(
                e -> mapper.map(e, ToolingDto.class)
        ).collect(toList()));
    }

    @Override
    protected void mapSpecificFields(SetupDto source, Setup destination) {
        destination.setAdditionalTools(source.getAdditionalToolDtoList().stream().map(
                e -> mapper.map(e, AdditionalTool.class)
        ).collect(toList()));
        destination.setSpecialTools(source.getSpecialToolDtoList().stream().map(
                e -> mapper.map(e, SpecialTool.class)
        ).collect(toList()));
        destination.setCutterTools(source.getCutterToolDtoList().stream().map(
                e -> mapper.map(e, CutterTool.class)
        ).collect(toList()));
        destination.setProductionUnit(mapper.map(source.getProductionUnitDto(), ProductionUnit.class));
        destination.setMeasureTools(source.getMeasureToolDtoList().stream().map(
                e -> mapper.map(e, MeasureTool.class)
        ).collect(toList()));
        destination.setToolings(source.getToolingDtoList().stream().map(
                e -> mapper.map(e, Tooling.class)
        ).collect(toList()));
    }
}