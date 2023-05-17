package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.AdditionalTool;

public interface AdditionalToolRepository extends JpaRepository<AdditionalTool, Long> {
}