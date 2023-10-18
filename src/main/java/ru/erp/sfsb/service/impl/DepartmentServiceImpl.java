package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.DepartmentDto;
import ru.erp.sfsb.mapper.DepartmentMapper;
import ru.erp.sfsb.model.Department;
import ru.erp.sfsb.repository.DepartmentRepository;
import ru.erp.sfsb.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl extends AbstractService<DepartmentDto, Department, DepartmentRepository, DepartmentMapper>
        implements DepartmentService {
    public DepartmentServiceImpl(DepartmentMapper mapper, DepartmentRepository repository) {
        super(mapper, repository, "Department");
        this.mapper = mapper;
        this.repository = repository;
    }
}