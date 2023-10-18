package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.CutterToolDto;
import ru.erp.sfsb.mapper.CutterToolMapper;
import ru.erp.sfsb.model.CutterTool;
import ru.erp.sfsb.repository.CutterToolRepository;
import ru.erp.sfsb.service.CutterToolService;

@Service
@Transactional
public class CutterToolServiceImpl extends AbstractService<CutterToolDto, CutterTool, CutterToolRepository, CutterToolMapper>
        implements CutterToolService {

    public CutterToolServiceImpl(CutterToolMapper mapper, CutterToolRepository repository) {
        super(mapper, repository, "Cutter tool");
    }
}