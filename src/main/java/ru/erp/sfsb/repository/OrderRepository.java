package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.erp.sfsb.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = """
            SELECT o.id,
                   o.created,
                   o.updated,
                   o.application_number,
                   o.business_proposal,
                   o.description,
                   o.customer_id,
                   o.user_uuid,
                   t.drawing_name
            FROM orders o
                     INNER JOIN items i ON i.order_id = o.id
                     INNER JOIN technologies t on i.technology_id = t.id
            WHERE t.drawing_name ILIKE CONCAT('%', :query, '%')
               OR t.drawing_number ILIKE CONCAT('%', :query, '%')
               OR o.description ILIKE CONCAT('%', :query, '%')
               OR CAST(o.application_number AS text) ILIKE CONCAT('%', :query, '%')
               """, nativeQuery = true)
    List<Order> getOrdersByQueryString(@Param("query") String query);
}