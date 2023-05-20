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
        storeDto.setMeasuringToolsDto(storePostDto.getMeasuringToolsDto().entrySet().stream().collect(Collectors.toMap(
                entry -> (MeasureToolDto) setId(new MeasureToolDto(), entry.getKey().longValue()),
                Map.Entry::getValue)
        ));
        storeDto.setCuttingToolsDto(storePostDto.getCuttingToolsDto().entrySet().stream().collect(Collectors.toMap(
                entry -> (CutterToolDto) setId(new CutterToolDto(), entry.getKey().longValue()),
                Map.Entry::getValue
        )));
        storeDto.setWorkpiecesDto(storePostDto.getWorkpiecesDto().entrySet().stream().collect(Collectors.toMap(
                entry -> (WorkpieceDto) setId(new WorkpieceDto(), entry.getKey().longValue()),
                Map.Entry::getValue
        )));
        return storeDto;
    }

    private AbstractDto setId(AbstractDto dto, Long id) {
        dto.setId(id);
        return dto;
    }
}