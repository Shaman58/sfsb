package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.SetupDto;
import ru.erp.sfsb.mapper.SetupMapper;
import ru.erp.sfsb.model.Setup;
import ru.erp.sfsb.repository.repos.SetupEntityRepository;
import ru.erp.sfsb.service.SetupService;

import static ru.erp.sfsb.LogTag.SETUP_SERVICE;

@Service
@Transactional
public class SetupServiceImpl extends AbstractService<SetupDto, Setup, SetupEntityRepository, SetupMapper>
        implements SetupService {

    public SetupServiceImpl(SetupMapper mapper, SetupEntityRepository repository) {
        super(mapper, repository, "Setup", SETUP_SERVICE);
    }
}