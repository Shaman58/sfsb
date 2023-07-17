package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.ProductionArea;

import java.util.List;

public interface ProductionAreaRepository extends JpaRepository<ProductionArea, Long> {
    List<ProductionArea> getProductionAreasByCompanyId(Long id);
}