package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.CompanyDto;
import ru.erp.sfsb.mapper.CompanyMapper;
import ru.erp.sfsb.model.Company;
import ru.erp.sfsb.repository.CompanyRepository;
import ru.erp.sfsb.service.CompanyService;

import static ru.erp.sfsb.LogTag.COMPANY_SERVICE;

@Service
@Slf4j
@Transactional
public class CompanyServiceImpl extends AbstractService<CompanyDto, Company, CompanyRepository, CompanyMapper>
        implements CompanyService {

    public CompanyServiceImpl(CompanyMapper mapper, CompanyRepository repository) {
        super(mapper, repository, "Company", COMPANY_SERVICE);
    }
}