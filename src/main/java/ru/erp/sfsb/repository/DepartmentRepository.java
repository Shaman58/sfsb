package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findAllByCompany_id(Long companyId);
}