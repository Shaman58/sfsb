package ru.erp.sfsb.service;

import ru.erp.sfsb.dto.ProductionUnitDto;

import java.util.List;

public interface ProductionUnitService extends Service<ProductionUnitDto> {

    List<ProductionUnitDto> getAllByAreaId(Long areaId);
}