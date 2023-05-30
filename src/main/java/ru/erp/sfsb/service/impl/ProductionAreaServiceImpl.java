package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ProductionAreaDto;
import ru.erp.sfsb.mapper.ProductionAreaMapper;
import ru.erp.sfsb.model.ProductionArea;
import ru.erp.sfsb.repository.ProductionAreaRepository;
import ru.erp.sfsb.service.ProductionAreaService;
import ru.erp.sfsb.service.ProductionUnitService;
import ru.erp.sfsb.service.StoreService;

import java.util.List;

@Service
@Slf4j
public class ProductionAreaServiceImpl extends AbstractService<ProductionAreaDto, ProductionArea, ProductionAreaRepository, ProductionAreaMapper>
        implements ProductionAreaService {

    private final ProductionAreaMapper mapper;
    private final ProductionAreaRepository repository;
    private final StoreService storeService;
    private final ProductionUnitService productionUnitService;

    public ProductionAreaServiceImpl(ProductionAreaMapper mapper, ProductionAreaRepository repository, StoreService storeService, ProductionUnitService productionUnitService) {
        super(mapper, repository, "Production area");
        this.mapper = mapper;
        this.repository = repository;
        this.storeService = storeService;
        this.productionUnitService = productionUnitService;
    }

    @Override
    @Transactional
    public List<ProductionAreaDto> getAll() {
        log.info("Looking all Areas in DB");
        var productionAreas = repository.findAll().stream().map(mapper::toDto).toList();
        productionAreas.forEach(e -> e.setProductionUnitDtoList(productionUnitService.getAllByAreaId(e.getId())));
        return productionAreas;
    }

    @Override
    @Transactional
    public ProductionAreaDto save(ProductionAreaDto productionAreaDto) {
        log.info("Saving Production area into DB");
        productionAreaDto.setStoreDto(storeService.get(productionAreaDto.getStoreDto().getId()));
//        productionAreaDto.setProductionUnitDtoList(productionAreaDto.getProductionUnitDtoList().stream().map(e ->
//                productionUnitService.get(e.getId())).collect(toList()));
        var areaDto = mapper.toDto(repository.save(mapper.toEntity(productionAreaDto)));
        areaDto.setProductionUnitDtoList(productionUnitService.getAllByAreaId(areaDto.getId()));
        return areaDto;
    }
}