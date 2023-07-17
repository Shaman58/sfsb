package ru.erp.sfsb.service;

import ru.erp.sfsb.dto.CutterToolDto;

import java.util.Set;

public interface CutterToolService extends Service<CutterToolDto> {

    Set<CutterToolDto> getAllToolsByOrderId(Long id);
}