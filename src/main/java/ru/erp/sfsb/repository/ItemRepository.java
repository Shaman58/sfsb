package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> getItemsByOrderId(Long id);
}