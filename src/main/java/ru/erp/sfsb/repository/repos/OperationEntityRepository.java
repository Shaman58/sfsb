package ru.erp.sfsb.repository.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.erp.sfsb.model.Operation;
import ru.erp.sfsb.repository.EntityRepository;

import java.util.List;

@Repository
public interface OperationEntityRepository extends EntityRepository<Operation> {

    @Query("SELECT e FROM Operation e WHERE e.id > 2 AND e.deleted = false")
    List<Operation> findAllByIdGreaterThan2();
}