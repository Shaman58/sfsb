package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}