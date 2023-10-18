package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}