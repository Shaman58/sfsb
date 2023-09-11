package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.SetupDto;
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

    @Transactional
    public TechnologyDto save(TechnologyDto technologyDto) {
        log.info("Saving Technology into DB");
        technologyDto.getSetups().forEach(this::setSetup);
        return mapper.toDto(repository.save(mapper.toEntity(technologyDto)));
    }

    @Transactional
    public TechnologyDto update(TechnologyDto technologyDto) {
        log.info("Saving Technology into DB");
        get(technologyDto.getId());
        technologyDto.getSetups().forEach(this::setSetup);
        return mapper.toDto(repository.save(mapper.toEntity(technologyDto)));
    }

    private void setSetup(SetupDto setup) {
        setup.getAdditionalTools().forEach(tool -> tool.setSetup(setup));
        setup.getCutterToolItems().forEach(tool -> tool.setSetup(setup));
        setup.getSpecialToolItems().forEach(tool -> tool.setSetup(setup));
    }
}