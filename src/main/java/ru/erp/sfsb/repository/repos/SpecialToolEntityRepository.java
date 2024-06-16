package ru.erp.sfsb.repository.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.erp.sfsb.model.SpecialTool;
import ru.erp.sfsb.repository.EntityRepository;

import java.util.List;

@Repository
public interface SpecialToolEntityRepository extends EntityRepository<SpecialTool> {

    List<SpecialTool> getAllByToolNameContainingIgnoreCaseAndDeletedIsFalseOrDescriptionContainingIgnoreCaseAndDeletedFalseAndDeletedIsFalse(String toolName, String description, Pageable pageable);
}