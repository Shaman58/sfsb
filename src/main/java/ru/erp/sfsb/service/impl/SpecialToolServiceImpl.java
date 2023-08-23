package ru.erp.sfsb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.SpecialToolDto;
import ru.erp.sfsb.mapper.SpecialToolMapper;
import ru.erp.sfsb.model.SpecialTool;
import ru.erp.sfsb.repository.SpecialToolRepository;
import ru.erp.sfsb.service.SpecialToolService;

@Service
@Slf4j
public class SpecialToolServiceImpl extends AbstractService<SpecialToolDto, SpecialTool, SpecialToolRepository, SpecialToolMapper>
        implements SpecialToolService {

    public SpecialToolServiceImpl(SpecialToolMapper mapper, SpecialToolRepository repository) {
        super(mapper, repository, "Special tool");
    }
}