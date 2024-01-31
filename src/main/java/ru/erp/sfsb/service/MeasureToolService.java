package ru.erp.sfsb.service;

import org.springframework.data.domain.Pageable;
import ru.erp.sfsb.dto.MeasureToolDto;

import java.util.List;

public interface MeasureToolService extends Service<MeasureToolDto> {

    List<MeasureToolDto> getByFilter(String filter, Pageable pageable);
}