package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}