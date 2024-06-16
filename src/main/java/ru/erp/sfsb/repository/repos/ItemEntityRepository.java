package ru.erp.sfsb.repository.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.erp.sfsb.model.Item;
import ru.erp.sfsb.repository.EntityRepository;

import java.util.List;

@Repository
public interface ItemEntityRepository extends EntityRepository<Item> {

    @Query("SELECT i FROM Item i JOIN i.technology t WHERE t.blocked = :userUuid AND i.deleted = false")
    List<Item> getAllByTechnologyUser(@Param("userUuid") String uuid);
}