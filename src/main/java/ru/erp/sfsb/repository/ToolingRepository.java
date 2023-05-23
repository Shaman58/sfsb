package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Tooling;

public interface ToolingRepository extends JpaRepository<Tooling, Long> {
}