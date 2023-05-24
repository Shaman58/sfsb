package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Setup;

public interface SetupRepository extends JpaRepository<Setup, Long> {
}