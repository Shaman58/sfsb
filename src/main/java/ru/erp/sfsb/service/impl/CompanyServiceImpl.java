package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.CompanyDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.mapper.CompanyMapper;
import ru.erp.sfsb.model.Company;
import ru.erp.sfsb.repository.CompanyRepository;
import ru.erp.sfsb.service.CompanyService;

@Service
@Slf4j
@Transactional
public class CompanyServiceImpl extends AbstractService<CompanyDto, Company, CompanyRepository, CompanyMapper>
        implements CompanyService {

    public CompanyServiceImpl(CompanyMapper mapper, CompanyRepository repository) {
        super(mapper, repository, "Company");
    }

    @Override
    public CompanyDto getCompany() {
        log.info("Looking company in DB");
        return mapper.toDto(repository.findById(1L).orElseThrow(
                () -> new EntityNotFoundException("There is no company in DB")));
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) {
        log.info("Updating Company into DB");
        companyDto.setId(1L);
        return mapper.toDto(repository.save(mapper.toEntity(companyDto)));
    }
}