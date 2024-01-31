package ru.erp.sfsb.service;

import org.springframework.data.domain.Pageable;
import ru.erp.sfsb.dto.SpecialToolDto;

import java.util.List;

public interface SpecialToolService extends Service<SpecialToolDto> {

    List<SpecialToolDto> getByFilter(String filter, Pageable pageable);
}