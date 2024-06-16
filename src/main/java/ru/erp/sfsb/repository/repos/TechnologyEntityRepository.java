package ru.erp.sfsb.repository.repos;

import org.springframework.stereotype.Repository;
import ru.erp.sfsb.model.Technology;
import ru.erp.sfsb.repository.EntityRepository;

@Repository
public interface TechnologyEntityRepository extends EntityRepository<Technology> {
}