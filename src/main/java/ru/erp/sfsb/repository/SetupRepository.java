package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Setup;

import java.util.List;

public interface SetupRepository extends JpaRepository<Setup, Long> {

    List<Setup> getSetupsByTechnologyId(Long id);
}