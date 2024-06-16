package ru.erp.sfsb.repository.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.erp.sfsb.model.Order;
import ru.erp.sfsb.repository.EntityRepository;

import java.util.List;

@Repository
public interface OrderEntityRepository extends EntityRepository<Order> {

    @Query(value = """
            SELECT DISTINCT *
            FROM (SELECT o.id,
                         o.created,
                         o.updated,
                         o.application_number,
                         o.business_proposal,
                         o.description,
                         o.customer_id,
                         o.user_uuid,
                         o.deleted
                  FROM orders o
                           INNER JOIN items i ON i.order_id = o.id AND i.deleted = false
                           INNER JOIN technologies t ON i.technology_id = t.id AND t.deleted = false
                  WHERE t.drawing_name ILIKE CONCAT('%', :query, '%')
                     OR t.drawing_number ILIKE CONCAT('%', :query, '%')
                     OR o.description ILIKE CONCAT('%', :query, '%')
                  UNION ALL
                  SELECT o.id,
                         o.created,
                         o.updated,
                         o.application_number,
                         o.business_proposal,
                         o.description,
                         o.customer_id,
                         o.user_uuid,
                         o.deleted
                  FROM orders o
                  WHERE CAST(o.application_number AS text) ILIKE CONCAT('%', :query, '%') AND o.deleted = false) AS combined_results
            ORDER BY application_number
            LIMIT :pageSize OFFSET :offset
            """, nativeQuery = true)
    List<Order> getOrdersByQueryString(@Param("query") String query, @Param("pageSize") int pageSize, @Param("offset") int offset);
}