package ru.erp.sfsb.service;

import ru.erp.sfsb.dto.SetupDto;

import java.util.List;

public interface SetupService extends Service<SetupDto> {

    List<SetupDto> getTechnologySetups(Long id);
}