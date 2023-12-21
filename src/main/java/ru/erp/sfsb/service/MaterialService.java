package ru.erp.sfsb.service;

import ru.erp.sfsb.dto.MaterialDto;

import java.time.LocalDateTime;
import java.util.List;

public interface MaterialService extends Service<MaterialDto> {

    List<MaterialDto> getMaterialWithoutPrice();

    List<MaterialDto> getMaterialWithExpiredDate();
}