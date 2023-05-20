package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.ProductionArea;

public interface ProductionAreaRepository extends JpaRepository<ProductionArea, Long> {
}