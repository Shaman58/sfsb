package ru.erp.sfsb.service;

import ru.erp.sfsb.dto.TechnologyDto;

import java.util.List;

public interface TechnologyService extends Service<TechnologyDto> {

    List<TechnologyDto> getEmployeeTechnologies(Long id);
}