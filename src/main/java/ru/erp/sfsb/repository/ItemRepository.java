package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.erp.sfsb.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT i FROM items i JOIN technologies t ON i.technology_id = t.id WHERE t.user_uuid = :userUuid", nativeQuery = true)
    List<Item> getAllByTechnologyUser(@Param("userUuid") String uuid);
}