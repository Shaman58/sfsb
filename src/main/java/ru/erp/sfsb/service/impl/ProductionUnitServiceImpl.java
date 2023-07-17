package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ProductionUnitDto;
import ru.erp.sfsb.mapper.ProductionUnitMapper;
import ru.erp.sfsb.model.ProductionUnit;
import ru.erp.sfsb.repository.ProductionUnitRepository;
import ru.erp.sfsb.service.ProductionAreaService;
import ru.erp.sfsb.service.ProductionUnitService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class ProductionUnitServiceImpl extends AbstractService<ProductionUnitDto, ProductionUnit, ProductionUnitRepository, ProductionUnitMapper>
        implements ProductionUnitService {

    private final ProductionUnitRepository repository;
    private final ProductionUnitMapper mapper;
    private final ProductionAreaService productionAreaService;

    public ProductionUnitServiceImpl(ProductionUnitMapper mapper, ProductionUnitRepository repository, ProductionAreaService productionAreaService) {
        super(mapper, repository, "Production unit");
        this.repository = repository;
        this.mapper = mapper;
        this.productionAreaService = productionAreaService;
    }

    @Override
    @Transactional
    public List<ProductionUnitDto> getAllByAreaId(Long areaId) {
        log.info("Looking all Units with area id = {} in DB", areaId);
        return repository.findAllByProductionAreaId(areaId).stream().map(mapper::toDto).collect(toList());
    }

    @Override
    @Transactional
    public ProductionUnitDto saveInArea(ProductionUnitDto productionUnitDto, Long areaId) {
        log.info("Saving Unit into DB");
        productionUnitDto.setProductionArea(productionAreaService.get(areaId));
        return mapper.toDto(repository.save(mapper.toEntity(productionUnitDto)));
    }

    @Override
    @Transactional
    public ProductionUnitDto updateInArea(ProductionUnitDto productionUnitDto, Long areaId) {
        log.info("Saving Unit into DB");
        get(productionUnitDto.getId());
        productionUnitDto.setProductionArea(productionAreaService.get(areaId));
        return mapper.toDto(repository.save(mapper.toEntity(productionUnitDto)));
    }
}