package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.ProductionUnit;

import java.util.List;

public interface ProductionUnitRepository extends JpaRepository<ProductionUnit, Long> {

    List<ProductionUnit> findAllByProductionAreaId(Long areaId);
}