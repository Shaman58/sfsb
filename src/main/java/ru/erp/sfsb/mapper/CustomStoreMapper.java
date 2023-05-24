package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.*;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CustomStoreMapper {

    public StoreDto convert(StorePostDto storePostDto) {
        var storeDto = new StoreDto();
        storeDto.setStoreName(storePostDto.getStoreName());
        storeDto.setMeasureToolDtoIntegerMap(storePostDto.getMeasuringTools().entrySet().stream().collect(Collectors.toMap(
                entry -> (MeasureToolDto) setId(new MeasureToolDto(), entry.getKey().longValue()),
                Map.Entry::getValue)
        ));
        storeDto.setCutterToolDtoIntegerMap(storePostDto.getCuttingTools().entrySet().stream().collect(Collectors.toMap(
                entry -> (CutterToolDto) setId(new CutterToolDto(), entry.getKey().longValue()),
                Map.Entry::getValue
        )));
        storeDto.setWorkpieceDtoIntegerMap(storePostDto.getWorkpieces().entrySet().stream().collect(Collectors.toMap(
                entry -> (WorkpieceDto) setId(new WorkpieceDto(), entry.getKey().longValue()),
                Map.Entry::getValue
        )));
        storeDto.setToolingDtoIntegerMap(storePostDto.getToolings().entrySet().stream().collect(Collectors.toMap(
                entry -> (ToolingDto) setId(new ToolingDto(), entry.getKey().longValue()),
                Map.Entry::getValue
        )));
        storeDto.setSpecialToolDtoIntegerMap(storePostDto.getSpecialTools().entrySet().stream().collect(Collectors.toMap(
                entry -> (SpecialToolDto) setId(new SpecialToolDto(), entry.getKey().longValue()),
                Map.Entry::getValue
        )));
        return storeDto;
    }

    private AbstractDto setId(AbstractDto dto, Long id) {
        dto.setId(id);
        return dto;
    }
}