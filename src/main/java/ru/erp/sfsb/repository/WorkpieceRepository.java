package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Workpiece;

public interface WorkpieceRepository extends JpaRepository<Workpiece, Long> {
}