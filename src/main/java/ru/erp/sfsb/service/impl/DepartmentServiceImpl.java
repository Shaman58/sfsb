package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.DepartmentDto;
import ru.erp.sfsb.mapper.DepartmentMapper;
import ru.erp.sfsb.model.Department;
import ru.erp.sfsb.repository.DepartmentRepository;
import ru.erp.sfsb.service.DepartmentService;

import java.util.List;

@Service
@Slf4j
public class DepartmentServiceImpl extends AbstractService<DepartmentDto, Department, DepartmentRepository, DepartmentMapper>
        implements DepartmentService {

    private final DepartmentMapper mapper;
    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentMapper mapper, DepartmentRepository repository) {
        super(mapper, repository, "Department");
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<DepartmentDto> getCompanyDepartments(Long companyId) {
        log.info("Looking all Departments in DB by CompanyId={}", companyId);
        return repository.findAllByCompany_id(companyId).stream().map(mapper::toDto).toList();
    }
}