package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.erp.sfsb.model.Operation;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query("SELECT e FROM Operation e WHERE e.id > 2")
    List<Operation> findAllByIdGreaterThan2();
}