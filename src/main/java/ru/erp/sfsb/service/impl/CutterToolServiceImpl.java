package ru.erp.sfsb.service.impl;

import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.CutterToolDto;
import ru.erp.sfsb.mapper.CutterToolsMapper;
import ru.erp.sfsb.model.CutterTool;
import ru.erp.sfsb.repository.CutterToolRepository;
import ru.erp.sfsb.service.CutterToolService;

@Service
public class CutterToolServiceImpl extends AbstractService<CutterToolDto, CutterTool, CutterToolRepository, CutterToolsMapper>
        implements CutterToolService {

    public CutterToolServiceImpl(CutterToolsMapper mapper, CutterToolRepository repository) {
        super(mapper, repository, "Cutter tool");
    }
}