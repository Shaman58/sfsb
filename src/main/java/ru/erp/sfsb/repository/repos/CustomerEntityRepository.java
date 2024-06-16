package ru.erp.sfsb.repository.repos;

import org.springframework.stereotype.Repository;
import ru.erp.sfsb.model.Customer;
import ru.erp.sfsb.repository.EntityRepository;

@Repository
public interface CustomerEntityRepository extends EntityRepository<Customer> {
}