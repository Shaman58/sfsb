package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}