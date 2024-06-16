package ru.erp.sfsb.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.erp.sfsb.model.AbstractEntity;

import java.util.List;

public interface EntityRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {

    List<E> findAllByDeletedIsFalse();

    Page<E> findAllByDeletedIsFalse(Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} e SET e.deleted = true WHERE e.id = :id")
    void setDeletedById(@Param("id") Long id);
}