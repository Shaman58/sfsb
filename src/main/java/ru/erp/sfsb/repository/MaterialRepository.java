package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}