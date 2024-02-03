package ru.erp.sfsb.service;

import org.springframework.data.domain.Pageable;
import ru.erp.sfsb.dto.ToolingDto;

import java.util.List;

public interface ToolingService extends Service<ToolingDto> {

    List<ToolingDto> getByFilter(String filter, Pageable pageable);
}