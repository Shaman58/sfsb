package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.erp.sfsb.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT i.id, i.created, i.updated, i.is_customer_material, i.quantity, i.technology_id, i.order_id, i.price_amount, i.price_currency FROM items i JOIN technologies t ON i.technology_id = t.id WHERE t.blocked = :userUuid", nativeQuery = true)
    List<Item> getAllByTechnologyUser(@Param("userUuid") String uuid);
}