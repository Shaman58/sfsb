package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}