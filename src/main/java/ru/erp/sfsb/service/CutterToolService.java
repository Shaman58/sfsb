package ru.erp.sfsb.service;

import org.springframework.data.domain.Pageable;
import ru.erp.sfsb.dto.CutterToolDto;

import java.util.List;

public interface CutterToolService extends Service<CutterToolDto> {

    List<CutterToolDto> getByFilter(String filter, Pageable pageable);
}