package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.SetupDto;
import ru.erp.sfsb.mapper.SetupMapper;
import ru.erp.sfsb.model.Setup;
import ru.erp.sfsb.repository.SetupRepository;
import ru.erp.sfsb.service.SetupService;
import ru.erp.sfsb.service.TechnologyService;

import java.util.List;

@Service
@Slf4j
public class SetupServiceImpl extends AbstractService<SetupDto, Setup, SetupRepository, SetupMapper>
        implements SetupService {

    private final TechnologyService technologyService;

    public SetupServiceImpl(SetupMapper mapper, SetupRepository repository, TechnologyService technologyService) {
        super(mapper, repository, "Setup");
        this.technologyService = technologyService;
    }

    @Override
    @Transactional
    public List<SetupDto> getTechnologySetups(Long id) {
        log.info("Looking all setups of technology id = {} in DB", id);
        return technologyService.get(id).getSetups();
    }
}