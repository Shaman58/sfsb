package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}