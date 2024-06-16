package ru.erp.sfsb.repository.repos;

import org.springframework.stereotype.Repository;
import ru.erp.sfsb.model.Workpiece;
import ru.erp.sfsb.repository.EntityRepository;

@Repository
public interface WorkpieceEntityRepository extends EntityRepository<Workpiece> {
}