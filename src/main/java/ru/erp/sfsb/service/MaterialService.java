package ru.erp.sfsb.service;

import org.springframework.data.domain.Pageable;
import ru.erp.sfsb.dto.MaterialDto;
import ru.erp.sfsb.model.Geometry;

import java.util.List;

public interface MaterialService extends Service<MaterialDto> {

    List<MaterialDto> getMaterialWithoutPrice();

    List<MaterialDto> getMaterialWithExpiredDate();

    MaterialDto updatePrice(MaterialDto materialDto);

    List<MaterialDto> getByFilter(String filter, Geometry geometry, Pageable pageable);
}