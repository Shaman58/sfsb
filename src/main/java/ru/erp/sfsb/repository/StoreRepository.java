package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.erp.sfsb.model.Store;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> getStoresByProductionAreaId(Long id);

    // обычный delete не удаляет. Хз почему. Пришлось делать кастом метод
    @Modifying
    @Query("delete from Store s where s.id=:id")
    void manualRemoveById(Long id);
}