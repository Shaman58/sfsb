package ru.erp.sfsb.repository.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.erp.sfsb.model.Tooling;
import ru.erp.sfsb.repository.EntityRepository;

import java.util.List;

@Repository
public interface ToolingEntityRepository extends EntityRepository<Tooling> {

    List<Tooling> getAllByToolNameContainingIgnoreCaseAndDeletedIsFalseOrDescriptionContainingIgnoreCaseAndDeletedFalseAndDeletedIsFalse(String toolName, String description, Pageable pageable);
}