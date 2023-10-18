package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.SetupDto;
import ru.erp.sfsb.mapper.SetupMapper;
import ru.erp.sfsb.model.Setup;
import ru.erp.sfsb.repository.SetupRepository;
import ru.erp.sfsb.service.SetupService;

@Service
@Transactional
public class SetupServiceImpl extends AbstractService<SetupDto, Setup, SetupRepository, SetupMapper>
        implements SetupService {

    public SetupServiceImpl(SetupMapper mapper, SetupRepository repository) {
        super(mapper, repository, "Setup");
    }
}