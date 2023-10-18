package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.mapper.TechnologyMapper;
import ru.erp.sfsb.model.Technology;
import ru.erp.sfsb.repository.TechnologyRepository;
import ru.erp.sfsb.service.TechnologyService;

@Service
@Transactional
public class TechnologyServiceImpl extends AbstractService<TechnologyDto, Technology, TechnologyRepository, TechnologyMapper>
        implements TechnologyService {

    public TechnologyServiceImpl(TechnologyMapper mapper, TechnologyRepository repository) {
        super(mapper, repository, "Technology");
        this.mapper = mapper;
        this.repository = repository;
    }
}