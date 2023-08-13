package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.StoreDto;
import ru.erp.sfsb.mapper.StoreMapper;
import ru.erp.sfsb.model.Store;
import ru.erp.sfsb.repository.StoreRepository;
import ru.erp.sfsb.service.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


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
    private final ProductionAreaService areaService;

    public StoreServiceImpl(StoreMapper mapper, StoreRepository repository, MeasureToolService measureToolService, CutterToolService cutterToolService, WorkpieceService workpieceService, ToolingService toolingService, SpecialToolService specialToolService, ProductionAreaService areaService) {
        super(mapper, repository, "Store");
        this.mapper = mapper;
        this.repository = repository;
        this.measureToolService = measureToolService;
        this.cutterToolService = cutterToolService;
        this.workpieceService = workpieceService;
        this.toolingService = toolingService;
        this.specialToolService = specialToolService;
        this.areaService = areaService;
    }

    @Override
    @Transactional
    public List<StoreDto> getAllByAreaId(Long id) {
        areaService.get(id);
        log.info("Looking all stores with area id={} in DB", id);
        return repository.getStoresByProductionAreaId(id)
                .stream().map(mapper::toDto).collect(toList());
    }

    @Override
    @Transactional
    public StoreDto saveInArea(StoreDto storeDto, Long id) {
        log.info("Saving Store into DB");
        fillStore(storeDto);
        storeDto.setProductionArea((areaService.get(id)));
        return mapper.toDto(repository.save(mapper.toEntity(storeDto)));
    }

    @Override
    @Transactional
    public StoreDto update(StoreDto storeDto) {
        log.info("Updating Store in DB");
        storeDto.setProductionArea(get(storeDto.getId()).getProductionArea());
        return mapper.toDto(repository.save(mapper.toEntity(storeDto)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting Store with id {} in DB", id);
        get(id);
        repository.manualRemoveById(id);
    }


    // метод необходим для обеспечения целостности данных Map в store
    private void fillStore(StoreDto storeDto) {
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
    }
}