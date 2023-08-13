package ru.erp.sfsb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.mapper.TechnologyMapper;
import ru.erp.sfsb.model.Technology;
import ru.erp.sfsb.repository.TechnologyRepository;
import ru.erp.sfsb.service.TechnologyService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class TechnologyServiceImpl extends AbstractService<TechnologyDto, Technology, TechnologyRepository, TechnologyMapper>
        implements TechnologyService {

    public TechnologyServiceImpl(TechnologyMapper mapper, TechnologyRepository repository) {
        super(mapper, repository, "Technology");
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<TechnologyDto> getEmployeeTechnologies(Long id) {
        log.info("Looking Employee {} Technologies in DB", id);
        var entities = repository.findAllByEmployeeId(id);
        return entities.stream().map(mapper::toDto).collect(toList());
    }
}