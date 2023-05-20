package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}