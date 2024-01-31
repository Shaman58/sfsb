package ru.erp.sfsb.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.MeasureTool;

import java.util.List;

public interface MeasureToolRepository extends JpaRepository<MeasureTool, Long> {

    List<MeasureTool> getAllByToolNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String toolName, String description, Pageable pageable);
}