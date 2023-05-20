package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.StoreDto;
import ru.erp.sfsb.mapper.StoreMapper;
import ru.erp.sfsb.model.Store;
import ru.erp.sfsb.repository.StoreRepository;
import ru.erp.sfsb.service.CutterToolService;
import ru.erp.sfsb.service.MeasureToolService;
import ru.erp.sfsb.service.StoreService;
import ru.erp.sfsb.service.WorkpieceService;

import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class StoreServiceImpl extends AbstractService<StoreDto, Store, StoreRepository, StoreMapper> implements StoreService {

    private final StoreMapper mapper;
    private final StoreRepository repository;
    private final MeasureToolService measureToolService;
    private final CutterToolService cutterToolService;
    private final WorkpieceService workpieceService;

    public StoreServiceImpl(StoreMapper mapper, StoreRepository repository, MeasureToolService measureToolService, CutterToolService cutterToolService, WorkpieceService workpieceService) {
        super(mapper, repository, "Store");
        this.mapper = mapper;
        this.repository = repository;
        this.measureToolService = measureToolService;
        this.cutterToolService = cutterToolService;
        this.workpieceService = workpieceService;
    }

    @Override
    @Transactional
    public StoreDto save(StoreDto storeDto) {
        log.info("Saving Store into DB");
        storeDto.setMeasuringToolsDto(storeDto.getMeasuringToolsDto().entrySet().stream().collect(Collectors.toMap(
                entry -> measureToolService.get(entry.getKey().getId()),
                Map.Entry::getValue
        )));
        storeDto.setWorkpiecesDto(storeDto.getWorkpiecesDto().entrySet().stream().collect(Collectors.toMap(
                entry -> workpieceService.get(entry.getKey().getId()),
                Map.Entry::getValue
        )));
        storeDto.setCuttingToolsDto(storeDto.getCuttingToolsDto().entrySet().stream().collect(Collectors.toMap(
                entry -> cutterToolService.get(entry.getKey().getId()),
                Map.Entry::getValue
        )));
        return mapper.toDto(repository.save(mapper.toEntity(storeDto)));
    }
}