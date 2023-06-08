package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.StoreDto;
import ru.erp.sfsb.mapper.StoreMapper;
import ru.erp.sfsb.model.Store;
import ru.erp.sfsb.repository.StoreRepository;
import ru.erp.sfsb.service.*;

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
    private final ToolingService toolingService;
    private final SpecialToolService specialToolService;

    public StoreServiceImpl(StoreMapper mapper, StoreRepository repository, MeasureToolService measureToolService, CutterToolService cutterToolService, WorkpieceService workpieceService, ToolingService toolingService, SpecialToolService specialToolService) {
        super(mapper, repository, "Store");
        this.mapper = mapper;
        this.repository = repository;
        this.measureToolService = measureToolService;
        this.cutterToolService = cutterToolService;
        this.workpieceService = workpieceService;
        this.toolingService = toolingService;
        this.specialToolService = specialToolService;
    }

    @Override
    @Transactional
    public StoreDto save(StoreDto storeDto) {
        log.info("Saving Store into DB");
        storeDto.setMeasureTools(storeDto.getMeasureTools().entrySet().stream().collect(Collectors.toMap(
                entry -> measureToolService.get(entry.getKey().getId()),
                Map.Entry::getValue
        )));
        storeDto.setWorkpieces(storeDto.getWorkpieces().entrySet().stream().collect(Collectors.toMap(
                entry -> workpieceService.get(entry.getKey().getId()),
                Map.Entry::getValue
        )));
        storeDto.setCutterTools(storeDto.getCutterTools().entrySet().stream().collect(Collectors.toMap(
                entry -> cutterToolService.get(entry.getKey().getId()),
                Map.Entry::getValue
        )));
        storeDto.setToolings(storeDto.getToolings().entrySet().stream().collect(Collectors.toMap(
                entry -> toolingService.get(entry.getKey().getId()),
                Map.Entry::getValue
        )));
        storeDto.setSpecialTools(storeDto.getSpecialTools().entrySet().stream().collect(Collectors.toMap(
                entry -> specialToolService.get(entry.getKey().getId()),
                Map.Entry::getValue
        )));
        return mapper.toDto(repository.save(mapper.toEntity(storeDto)));
    }
}