package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.erp.sfsb.model.File;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> getFilesByOrderId(Long orderId);

    @Modifying
    @Query(value = "DELETE FROM files WHERE id = :id", nativeQuery = true)
    void removeById(@Param("id") Long id);
}