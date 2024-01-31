package ru.erp.sfsb.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.CutterTool;

import java.util.List;

public interface CutterToolRepository extends JpaRepository<CutterTool, Long> {

    List<CutterTool> getAllByToolNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String toolName, String description, Pageable pageable);
}