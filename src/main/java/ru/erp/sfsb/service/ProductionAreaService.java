package ru.erp.sfsb.service;

import ru.erp.sfsb.dto.ProductionAreaDto;

import java.util.List;

public interface ProductionAreaService extends Service<ProductionAreaDto> {
    List<ProductionAreaDto> getAllByCompanyId(Long id);
}