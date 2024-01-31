package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.CutterToolDto;
import ru.erp.sfsb.mapper.CutterToolMapper;
import ru.erp.sfsb.model.CutterTool;
import ru.erp.sfsb.repository.CutterToolRepository;
import ru.erp.sfsb.service.CutterToolService;

import java.util.List;

@Service
@Slf4j
@Transactional
public class CutterToolServiceImpl extends AbstractService<CutterToolDto, CutterTool, CutterToolRepository, CutterToolMapper>
        implements CutterToolService {

    public CutterToolServiceImpl(CutterToolMapper mapper, CutterToolRepository repository) {
        super(mapper, repository, "Cutter tool");
    }

    @Override
    public List<CutterToolDto> getByFilter(String filter, Pageable pageable) {
        log.info("Looking all Cutter tools in DB by filter {}", filter);
        return mapper.toDto(repository
                .getAllByToolNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(filter, filter, pageable));
    }
}