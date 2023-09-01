package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.ProductionUnit;

public interface ProductionUnitRepository extends JpaRepository<ProductionUnit, Long> {
}