package ru.erp.sfsb.service.impl;

import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ProductionUnitDto;
import ru.erp.sfsb.mapper.ProductionUnitMapper;
import ru.erp.sfsb.model.ProductionUnit;
import ru.erp.sfsb.repository.ProductionUnitRepository;
import ru.erp.sfsb.service.ProductionUnitService;

@Service
public class ProductionUnitServiceImpl extends AbstractService<ProductionUnitDto, ProductionUnit, ProductionUnitRepository, ProductionUnitMapper>
        implements ProductionUnitService {

    public ProductionUnitServiceImpl(ProductionUnitMapper mapper, ProductionUnitRepository repository) {
        super(mapper, repository, "Production unit");
    }
}