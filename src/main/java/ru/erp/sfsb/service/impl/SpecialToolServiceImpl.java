package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.SpecialToolDto;
import ru.erp.sfsb.mapper.SpecialToolMapper;
import ru.erp.sfsb.model.SpecialTool;
import ru.erp.sfsb.repository.SpecialToolRepository;
import ru.erp.sfsb.service.SpecialToolService;

import java.util.List;

@Service
@Slf4j
@Transactional
public class SpecialToolServiceImpl extends AbstractService<SpecialToolDto, SpecialTool, SpecialToolRepository, SpecialToolMapper>
        implements SpecialToolService {

    public SpecialToolServiceImpl(SpecialToolMapper mapper, SpecialToolRepository repository) {
        super(mapper, repository, "Special tool");
    }

    @Override
    public List<SpecialToolDto> getByFilter(String filter, Pageable pageable) {
        log.info("Looking all Special tools in DB by filter {}", filter);
        return mapper.toDto(repository
                .getAllByToolNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(filter, filter, pageable));
    }
}