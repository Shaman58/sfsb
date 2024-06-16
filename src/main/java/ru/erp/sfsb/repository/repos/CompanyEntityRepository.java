package ru.erp.sfsb.repository.repos;

import org.springframework.stereotype.Repository;
import ru.erp.sfsb.model.Company;
import ru.erp.sfsb.repository.EntityRepository;

@Repository
public interface CompanyEntityRepository extends EntityRepository<Company> {
}