package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.erp.sfsb.model.File;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {


    @Query(value = "SELECT f FROM files f JOIN order_files of ON of.file_id = f.id WHERE of.order_id = :orderId", nativeQuery = true)
    List<File> getFilesByOrderId(@Param("orderId") Long orderId);

    @Modifying
    @Query(value = "DELETE FROM files WHERE id = :id", nativeQuery = true)
    void removeById(@Param("id") Long id);
}