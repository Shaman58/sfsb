package ru.erp.sfsb.repository.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.erp.sfsb.model.MeasureTool;
import ru.erp.sfsb.repository.EntityRepository;

import java.util.List;

@Repository
public interface MeasureToolEntityRepository extends EntityRepository<MeasureTool> {

    List<MeasureTool> getAllByToolNameContainingIgnoreCaseAndDeletedIsFalseOrDescriptionContainingIgnoreCaseAndDeletedFalseAndDeletedIsFalse(String toolName, String description, Pageable pageable);
}