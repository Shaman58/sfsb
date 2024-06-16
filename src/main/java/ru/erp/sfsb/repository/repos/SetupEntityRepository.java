package ru.erp.sfsb.repository.repos;

import org.springframework.stereotype.Repository;
import ru.erp.sfsb.model.Setup;
import ru.erp.sfsb.repository.EntityRepository;

@Repository
public interface SetupEntityRepository extends EntityRepository<Setup> {
}