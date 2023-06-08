package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.erp.sfsb.dto.ProductionAreaDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.mapper.ProductionAreaMapper;
import ru.erp.sfsb.model.ProductionArea;
import ru.erp.sfsb.repository.ProductionAreaRepository;
import ru.erp.sfsb.service.ProductionAreaService;
import ru.erp.sfsb.service.ProductionUnitService;
import ru.erp.sfsb.service.StoreService;

import java.util.List;

import static java.lang.String.format;

@Service
@Slf4j
public class ProductionAreaServiceImpl extends AbstractService<ProductionAreaDto, ProductionArea, ProductionAreaRepository, ProductionAreaMapper>
        implements ProductionAreaService {

    private final ProductionAreaMapper mapper;
    private final ProductionAreaRepository repository;
    private final ProductionUnitService productionUnitService;
    private final StoreService storeService;

    public ProductionAreaServiceImpl(ProductionAreaMapper mapper, ProductionAreaRepository repository, ProductionUnitService productionUnitService, StoreService storeService) {
        super(mapper, repository, "Production area");
        this.mapper = mapper;
        this.repository = repository;
        this.productionUnitService = productionUnitService;
        this.storeService = storeService;
    }

    @Override
    @Transactional
    public ProductionAreaDto get(@PathVariable Long id) {
        log.info("Looking Area with id={} in DB", id);
        var area = mapper.toDto((repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("There is no Area with id=%d in database", id)))));
        deleteAreaAndSetUnits(area);
        return area;
    }

    @Override
    @Transactional
    public List<ProductionAreaDto> getAll() {
        log.info("Looking all Areas in DB");
        var productionAreas = repository.findAll().stream().map(mapper::toDto).toList();
        productionAreas.forEach(this::deleteAreaAndSetUnits);
        return productionAreas;
    }

    @Override
    @Transactional
    public ProductionAreaDto save(ProductionAreaDto productionAreaDto) {
        log.info("Saving Production area into DB");
        if (productionAreaDto.getStore().getId() != 0) {
            productionAreaDto.setStore(storeService.get(productionAreaDto.getStore().getId()));
        } else {
            productionAreaDto.setStore(null);
        }
        var areaDto = mapper.toDto(repository.save(mapper.toEntity(productionAreaDto)));
        deleteAreaAndSetUnits(areaDto);
        return areaDto;
    }

    private void deleteAreaAndSetUnits(ProductionAreaDto area) {
        area.setProductionUnits(productionUnitService.getAllByAreaId(area.getId()));
        area.getProductionUnits().forEach(p -> p.setProductionArea(null));
    }
}