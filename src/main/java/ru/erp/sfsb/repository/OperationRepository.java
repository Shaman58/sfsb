package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}