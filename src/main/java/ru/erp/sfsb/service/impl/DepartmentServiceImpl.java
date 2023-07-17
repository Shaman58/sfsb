package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.DepartmentDto;
import ru.erp.sfsb.exception.EntityReferenceException;
import ru.erp.sfsb.mapper.DepartmentMapper;
import ru.erp.sfsb.model.Department;
import ru.erp.sfsb.repository.DepartmentRepository;
import ru.erp.sfsb.service.CompanyService;
import ru.erp.sfsb.service.DepartmentService;
import ru.erp.sfsb.service.EmployeeService;

import java.util.List;

@Service
@Slf4j
public class DepartmentServiceImpl extends AbstractService<DepartmentDto, Department, DepartmentRepository, DepartmentMapper>
        implements DepartmentService {

    private final DepartmentMapper mapper;
    private final DepartmentRepository repository;
    private final CompanyService companyService;

    public DepartmentServiceImpl(DepartmentMapper mapper, DepartmentRepository repository, CompanyService companyService) {
        super(mapper, repository, "Department");
        this.mapper = mapper;
        this.repository = repository;
        this.companyService = companyService;
    }

    @Override
    @Transactional
    public List<DepartmentDto> getCompanyDepartments(Long companyId, Pageable pageable) {
        log.info("Looking all Departments in DB by CompanyId={}", companyId);
        return repository.findAllByCompany_id(companyId, pageable).stream().map(mapper::toDto).toList();
    }

    @Override
    @Transactional
    public DepartmentDto save(DepartmentDto departmentDto) {
        log.info("Saving department into DB");
        departmentDto.setCompany(companyService.get(departmentDto.getCompany().getId()));
        return mapper.toDto(repository.save(mapper.toEntity(departmentDto)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting department with id {} in DB", id);
        if (get(id).getEmployees().size() == 0) {
            repository.deleteById(id);
        } else {
            throw new EntityReferenceException(String.format("Department with id=%d has employees", id));
        }
    }
}