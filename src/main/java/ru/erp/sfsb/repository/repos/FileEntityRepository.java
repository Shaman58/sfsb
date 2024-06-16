package ru.erp.sfsb.repository.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.erp.sfsb.model.File;
import ru.erp.sfsb.repository.EntityRepository;

import java.util.List;

@Repository
public interface FileEntityRepository extends EntityRepository<File> {

    @Query(value = "SELECT f.filename, f.link, f.id, f.created, f.updated, f.user_uuid FROM files f JOIN order_files of ON of.file_id = f.id WHERE of.order_id = :orderId", nativeQuery = true)
    List<File> getFilesByOrderId(@Param("orderId") Long orderId);

    @Modifying
    @Query(value = "DELETE FROM files WHERE id = :id", nativeQuery = true)
    void removeById(@Param("id") Long id);
}