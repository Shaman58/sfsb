package ru.erp.sfsb.service;

import ru.erp.sfsb.dto.StoreDto;

import java.util.List;

public interface StoreService extends Service<StoreDto> {

    List<StoreDto> getAllByAreaId(Long id);

    StoreDto saveInArea(StoreDto storeDto, Long id);
}