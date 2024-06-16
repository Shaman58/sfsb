package ru.erp.sfsb.repository.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.erp.sfsb.model.CutterTool;
import ru.erp.sfsb.repository.EntityRepository;

import java.util.List;

@Repository
public interface CutterToolEntityRepository extends EntityRepository<CutterTool> {
    List<CutterTool> getAllByToolNameContainingIgnoreCaseAndDeletedIsFalseOrDescriptionContainingIgnoreCaseAndDeletedIsFalse(String toolName, String description, Pageable pageable);
}